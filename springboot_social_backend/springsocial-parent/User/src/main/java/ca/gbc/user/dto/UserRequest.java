package ca.gbc.user.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

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
    @Email
    private String email;

}
