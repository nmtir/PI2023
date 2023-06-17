package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Message;
import tn.eesprit.gestionevenementback.Entities.Post;
import tn.eesprit.gestionevenementback.Entities.Forum;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Repository.ForumRepository;
import tn.eesprit.gestionevenementback.Repository.PostRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService{
    private final PostRepository PostRepo;
    private final ForumRepository forumRepo;
    private final UserRepository userRepository;

    @Override
    public List<Post> retrieveAllPosts(){return PostRepo.findAll();}
    @Override
    public Post addOrUpdatePost(Post post){return PostRepo.save(post);}
    @Override
    public Post checkViewAndUpdatePost(Post post,Integer id){
        User user=userRepository.findById(id).orElse(null);
        Post p=PostRepo.findById(post.getPostId()).orElse(null);
        for (User u:p.getViews())
        {
            if (u==user)
            {
                return p;
            }
        }
        p.getViews().add(user);
        return PostRepo.save(p);

    }
    @Override
    public Post addAndAssignPostToForum(Post post,Integer id){
        Forum forum=forumRepo.findById(id).orElse(null);
        post.setForum(forum);
        return PostRepo.save(post);
    }


    @Override
    public Post checkLikeAndUpdatePost(Post post, Integer id){
        Post p=PostRepo.findById(post.getPostId()).orElse(null);
        User user=userRepository.findById(id).orElse(null);
        for (User u:p.getLikes())
        {
            if (u==user)
            {
                return p;
            }
        }
        p.getLikes().add(user);
        return PostRepo.save(p);

    }
    @Override
    public Post removeLike(Post post,Integer id){
        Post p=PostRepo.findById(post.getPostId()).orElse(null);
        User user=userRepository.findById(id).orElse(null);
        for (User u:p.getLikes())
        {
            if (u==user)
            {
                p.getLikes().remove(u);
                return PostRepo.save(p);
            }
        }
        return p;

    }



    @Override
    public Post retrievePost(Integer id){return PostRepo.findById(id).orElse(null);}
    @Override
    public void removePost(Integer id){PostRepo.deleteById(id);}

}
