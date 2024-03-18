package ca.gbc.post.service;

import ca.gbc.post.dto.CommentResponse;
import ca.gbc.post.dto.PostRequest;
import ca.gbc.post.dto.PostResponse;

import java.util.List;

public interface PostService {

    List<PostResponse> getPostsByUserId(String userId);

    void createPost(PostRequest postRequest);

    List<PostResponse> getAllPosts();

    List<CommentResponse> getCommentsForPost(String postId);
}
