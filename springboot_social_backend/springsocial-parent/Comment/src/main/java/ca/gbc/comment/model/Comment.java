package ca.gbc.comment.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="t_comment")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;


    private String postId;
}
