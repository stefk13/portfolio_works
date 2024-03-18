package ca.gbc.comment.controller;

import ca.gbc.comment.dto.CommentRequest;
import ca.gbc.comment.dto.CommentResponse;
import ca.gbc.comment.dto.PostResponse;
import ca.gbc.comment.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentServiceImpl commentService;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public String makeComment(@RequestBody CommentRequest commentRequest){
//        commentService.makeComment(commentRequest);
//        return "Comment Created Sucessfully";
//    }

    @PostMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String makeComment(@PathVariable String postId, @RequestBody CommentRequest commentRequest){
        commentService.makeComment(commentRequest);
        return "Comment Created Successfully";
    }

    @GetMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getPostsForComment(@PathVariable("commentId") String commentId) {
        return commentService.getPostsForComment(commentId);
    }

    @GetMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponse> getCommentsForPost(@PathVariable("postId") String postId) {
        return commentService.getCommentsForPost(postId);
    }

}
