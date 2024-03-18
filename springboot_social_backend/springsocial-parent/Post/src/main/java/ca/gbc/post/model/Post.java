package ca.gbc.post.model;


//import ca.gbc.comment.model.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.hibernate.annotations.Fetch;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;
    public String title;
    public String content;
    public String userId;
}
