package ca.gbc.post.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

//    @NonNull
    private String username;

//    @NonNull
//    @Email
    private String email;

}
