package tn.eesprit.gestionevenementback.Services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.suuft.libretranslate.Language;
import net.suuft.libretranslate.Translator;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Message;
import tn.eesprit.gestionevenementback.Entities.Post;
import tn.eesprit.gestionevenementback.Entities.Forum;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Repository.ForumRepository;
import tn.eesprit.gestionevenementback.Repository.MessageRepository;
import tn.eesprit.gestionevenementback.Repository.PostRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService{
    private final PostRepository PostRepo;
    private final MessageRepository MessageRepo;
    private final ForumRepository forumRepo;
    private final UserRepository userRepository;

    @Override
    public List<Post> retrieveAllPosts(){return PostRepo.findAll();}
    @Override
    public Post addOrUpdatePost(Post post){return PostRepo.save(post);}
    @Override
    public Post checkViewAndUpdatePost(Post post,Long id){
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
    public Post addAndAssignPostToForum(Post post,Integer id,Long idUser){
        Forum forum=forumRepo.findById(id).orElse(null);
        post.setForum(forum);
        Date d=new Date();
        post.setDatePub(d);
        User user=userRepository.findById(idUser).orElse(null);
        post.setUser(user);
        return PostRepo.save(post);
    }


    @Override
    public Post checkLikeAndUpdatePost(Post post, Long id){
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
    public Post removeLike(Post post,Long id){
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
    public Post translatePost(Integer id,String from,String to){
        Post p= PostRepo.findById(id).orElse(null);
        for (Message m0:p.getMessages()) {
            if (from.contains("Auto")){
                m0.setContenu(Translator.translate(Language.valueOf(to.toUpperCase()),m0.getContenu()));
            }else{
                m0.setContenu(Translator.translate(Language.valueOf(from.toUpperCase()),Language.valueOf(to.toUpperCase()),m0.getContenu()));
            }
            Message msg=MessageRepo.findById(m0.getMessageId()).orElse(null);
            for (Message m1:msg.getMessages()) {
                if (from.contains("Auto")){
                    m1.setContenu(Translator.translate(Language.valueOf(to.toUpperCase()),m1.getContenu()));
                }else{
                    m1.setContenu(Translator.translate(Language.valueOf(from.toUpperCase()),Language.valueOf(to.toUpperCase()),m1.getContenu()));
                }
            }
        }
        return p;
    }
    @Override
    public void removePost(Integer id){PostRepo.deleteById(id);}

}
