package ca.gbc.comment.service;

import ca.gbc.comment.dto.CommentRequest;
import ca.gbc.comment.dto.CommentResponse;
import ca.gbc.comment.dto.PostResponse;
import ca.gbc.comment.model.Comment;
import ca.gbc.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final WebClient.Builder webClientBuilder;

    @Value("${post.service.url}")
    private String postServiceUrl;

    @Value("${comment.service.url}")
    private String commentServiceUrl;

    @Override
    public void makeComment(CommentRequest commentRequest) {

        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setPostId(commentRequest.getPostId());
        commentRepository.save(comment);
        log.info("Comment {} is saved", comment.getId());
    }

    public List<PostResponse> getPostsForComment(String commentId) {
        // Call the Post microservice to get posts associated with the specified comment
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(postServiceUrl + "/api/post/comments/{commentId}", commentId)
                    .retrieve()
                    .bodyToFlux(PostResponse.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            log.error("Posts not found for Comment ID: {}", commentId);
            return Collections.emptyList();
        } catch (Exception ex) {
            log.error("Error while fetching posts for Comment ID: {}", commentId, ex);
            throw ex; // handle or log other exceptions as needed
        }
    }

//    public List<CommentResponse> getCommentsForPost(String postId) {
//        List<Comment> comments = commentRepository.findByPostId(postId);
//        return comments.stream()
//                .map(comment -> CommentResponse.builder()
//                        .id(comment.getId())
//                        .content(comment.getContent())
//                        .postId(comment.getPostId())
//                        .post(getPostById(comment.getPostId())) // Fetch associated post
//                        .build())
//                .collect(Collectors.toList());
//    }

    public List<CommentResponse> getCommentsForPost(String postId) {
        // Call the Comment microservice to get comments for the specified post
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

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    private PostResponse getPostById(String postId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(postServiceUrl + "/api/post/{postId}", postId)
                    .retrieve()
                    .bodyToMono(PostResponse.class)
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            log.error("Post not found with ID: {}", postId);
            return null;
        } catch (Exception ex) {
            log.error("Error while fetching post with ID: {}", postId, ex);
            throw ex; // handle or log other exceptions as needed
        }
    }


//    private CommentItem mapToModel(CommentItem commentItem) {
//        CommentItem commentItem1 = new CommentItem();
//        commentItem1.setContent(commentItem.getContent());
//        return commentItem;
//    }
}
