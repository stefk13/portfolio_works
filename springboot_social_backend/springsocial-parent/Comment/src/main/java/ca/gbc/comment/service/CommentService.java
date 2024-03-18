package ca.gbc.comment.service;

import ca.gbc.comment.dto.CommentRequest;
import ca.gbc.comment.dto.CommentResponse;
import ca.gbc.comment.dto.PostResponse;
import ca.gbc.comment.model.Comment;

import java.util.List;

public interface CommentService {
    void makeComment(CommentRequest commentRequest);

    List<PostResponse> getPostsForComment(String commentId);

    List<CommentResponse> getCommentsForPost(String postId);

    List<Comment> getAllComments();
}
