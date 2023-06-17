package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Post;

import java.util.List;

public interface IPostService {
    List<Post> retrieveAllPosts();
    Post addOrUpdatePost(Post Post);
    Post retrievePost(Integer id);
    void removePost(Integer id);
    Post addAndAssignPostToForum(Post post,Integer id);
    Post checkViewAndUpdatePost(Post post,Integer id);
    Post checkLikeAndUpdatePost(Post post, Integer id);
    Post removeLike(Post post,Integer id);
}
