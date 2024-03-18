package ca.gbc.user.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
