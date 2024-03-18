package ca.gbc.post.service;

import ca.gbc.post.dto.CommentResponse;
import ca.gbc.post.dto.PostRequest;
import ca.gbc.post.dto.PostResponse;
import ca.gbc.post.dto.UserResponse;
import ca.gbc.post.model.Post;
import ca.gbc.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final MongoTemplate mongoTemplate;
    private final WebClient.Builder webClientBuilder;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Value("${comment.service.url}")
    private String commentServiceUrl;

    @Override
    public List<PostResponse> getPostsByUserId(String userId){
        return postRepository.findByUserId(userId);
    }

    @Override
    public void createPost(PostRequest postRequest) {
        log.info("Creating Post {}!", postRequest.getTitle());

        Post newPost = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .userId(postRequest.getUserId())
                .build();

        postRepository.save(newPost);

        log.info("Post {} was created and saved!", newPost.getId());

    }

    @Override
    public List<PostResponse> getAllPosts() {
        log.info("Returning all posts with comments!");

        List<Post> posts = postRepository.findAll();
        List<PostResponse> postResponses = new ArrayList<>();

        for (Post post : posts) {
            PostResponse postResponse = mapToPostResponseWithUser(post);
            List<CommentResponse> comments = getCommentsForPost(post.getId());
            postResponse.setComments(comments);
            postResponses.add(postResponse);
        }

        return postResponses;
    }
//    public List<PostResponse> getAllPosts() {
//        log.info("Returning all posts!");
////        List<PostResponse> post = postRepository.findAll().stream().map(this::mapToPostResponseWithUser).toList();
//////        return post.stream().map(this::mapToPostResponse).toList();
////        return post;
//        List<PostResponse> posts = postRepository.findAll().stream().map(this::mapToPostResponseWithUser).toList();
//        posts.forEach(post -> post.setComments(getCommentsForPost(post.getId())));
//        return posts;
//    }


    private UserResponse getUserById(String userId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(userServiceUrl + "/{userId}", userId)
                    .retrieve()
                    .bodyToMono(UserResponse.class)
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            log.error("User not found with ID: {}", userId);
            return null;
        } catch (Exception ex) {
            log.error("Error while fetching user with ID: {}", userId, ex);
            throw ex; // handle or log other exceptions as needed
        }
    }

    private PostResponse mapToPostResponseWithUser(Post post) {
        UserResponse user = getUserById(post.getUserId());
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .user(user)
                .build();
    }

    @Override
    public List<CommentResponse> getCommentsForPost(String postId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(commentServiceUrl + "/api/comment/posts/{postId}", postId)
                    .retrieve()
                    .bodyToFlux(CommentResponse.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            log.error("Comments not found for Post ID: {}", postId);
            return Collections.emptyList();
        } catch (Exception ex) {
            log.error("Error while fetching comments for Post ID: {}", postId, ex);
            throw ex; // handle or log other exceptions as needed
        }
    }

    public String getUserName(String userId){
        return webClientBuilder
                .build()
                .get()
                .uri(userServiceUrl + "/{userId}" + userId)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block()
                .getUsername();
    }


}
