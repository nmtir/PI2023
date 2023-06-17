package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Forum;
import tn.eesprit.gestionevenementback.Entities.Post;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Repository.ForumRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements IForumService{
    private final ForumRepository ForumRepo;
    @Override
    public List<Forum> retrieveAllForums(){return ForumRepo.findAll();}
    @Override
    public Forum addOrUpdateForum(Forum forum){return ForumRepo.save(forum);}
    @Override
    public Forum retrieveForum(Integer id){return ForumRepo.findById(id).orElse(null);}
    @Override
    public Forum retrieveForumByEventId(Integer id, String sortingType) {
        if (sortingType.equals("maxVues")) {
            Forum f = ForumRepo.findByEvent_EventId(id);
            Set<Post> posts = f.getPosts();
            List<Post> sortedPosts = posts.stream()
                    .sorted(Comparator.comparingInt(post -> post.getViews().size()))
                    .collect(Collectors.toList());
            Collections.reverse(sortedPosts);
            f.setPosts(new LinkedHashSet<>(sortedPosts));
            System.out.println(""+f.getPosts());
            return f;
        } else if (sortingType.equals("mostRecent")) {
            Forum f = ForumRepo.findByEvent_EventId(id);
            Set<Post> posts = f.getPosts();
            List<Post> sortedPosts = posts.stream()
                    .sorted(Comparator.comparing(Post::getDatePub).reversed())
                    .collect(Collectors.toList());
            f.setPosts(new LinkedHashSet<>(sortedPosts));
            return f;
        } else {
            return ForumRepo.findByEvent_EventId(id);
        }
    }
    @Override
    public void removeForum(Integer id){ForumRepo.deleteById(id);}

}
