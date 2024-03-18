package ca.gbc.post.repository;

import ca.gbc.post.dto.PostResponse;
import ca.gbc.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<PostResponse> findByUserId(String userId);
}
