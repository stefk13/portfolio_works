package ca.gbc.post.controller;

import ca.gbc.post.dto.CommentResponse;
import ca.gbc.post.dto.PostRequest;
import ca.gbc.post.dto.PostResponse;
import ca.gbc.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostRequest postRequest){
        postService.createPost(postRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getAllPosts(){return postService.getAllPosts();}

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getPostsByUserId(@PathVariable("userId") String userId){
        return postService.getPostsByUserId(userId);
    }

    @GetMapping("/comments/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponse> getCommentsForPost(@PathVariable("postId") String postId) {
        return postService.getCommentsForPost(postId);
    }

}
