package ca.gbc.post.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.xml.stream.events.Comment;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;
    private String title;
    private String content;
    private UserResponse user;
    private List<CommentResponse> comments;
}
