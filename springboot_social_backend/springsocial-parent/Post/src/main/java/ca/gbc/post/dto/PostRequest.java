package ca.gbc.post.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(value = "post")
public class PostRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;
    private String title;
    private String content;
    private String userId;

}
