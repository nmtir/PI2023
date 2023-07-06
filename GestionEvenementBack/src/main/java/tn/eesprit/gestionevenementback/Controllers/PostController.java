package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Message;
import tn.eesprit.gestionevenementback.Entities.Post;
import tn.eesprit.gestionevenementback.Services.IPostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final IPostService postService;
    @PostMapping("/add")
    Post addPost(@RequestBody Post post)
    {
        return postService.addOrUpdatePost(post);
    }
    @PostMapping("/add/{id}/{user}")
    Post addPostToForum(@RequestBody Post post,@PathVariable("id") Integer id,@PathVariable("user") Long idUser)
    {
        return postService.addAndAssignPostToForum(post,id,idUser);
    }
    @PutMapping("/update")
    Post updatePost(@RequestBody Post post){
        return postService.addOrUpdatePost(post);
    }
    @PutMapping("/views/update/{id}")
    Post checkViewsAndUpdatePost(@RequestBody Post post,@PathVariable("id") Long id){
        return postService.checkViewAndUpdatePost(post,id);
    }

    @PutMapping("/likes/update/{id}")
    Post checkLikesAndUpdatePost(@RequestBody Post post, @PathVariable("id") Long id){
        return postService.checkLikeAndUpdatePost(post,id);
    }
    @PutMapping("/likes/remove/{id}")
    Post removeLike(@RequestBody Post post,@PathVariable("id") Long id){
        return postService.removeLike(post,id);
    }

    @GetMapping("/get/{id}")
    Post getPost(@PathVariable("id") Integer id){
        return postService.retrievePost(id);
    }
    @GetMapping("/all")
    List<Post> getAllPosts(){return postService.retrieveAllPosts();}
    @DeleteMapping("/delete/{id}")
    void deletePost(@PathVariable("id") Integer id){
        postService.removePost(id);
    }

}
