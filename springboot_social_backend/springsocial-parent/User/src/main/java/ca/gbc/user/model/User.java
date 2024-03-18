package ca.gbc.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name="t_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

//    @NonNull
    private String username;

//    @NonNull
    @Email
    private String email;

}
