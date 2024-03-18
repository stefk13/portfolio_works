package ca.gbc.user.service;

//import ca.gbc.post.dto.PostResponse;
import ca.gbc.user.dto.PostResponse;
import ca.gbc.user.dto.UserRequest;
import ca.gbc.user.dto.UserResponse;
import ca.gbc.user.model.User;
import ca.gbc.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final WebClient.Builder webClientBuilder;

    @Value("${post.service.url}")
    private String postServiceUrl;

    @Override
    @Transactional
    public void createUser(UserRequest userRequest){

        log.info("Creating a new user with first name {} and last name {}", userRequest.getUsername(), userRequest.getEmail());

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());

        userRepository.save(user);

        log.info("User {} is saved", user.getId());

    }

    @Override
    @Transactional
    public String updateUser(String userId, UserRequest userRequest) {
        log.info("Updating a user with id {}", userId);

        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            user.setId(UUID.randomUUID().toString());
            user.setUsername(userRequest.getUsername());
            user.setEmail(userRequest.getEmail());

            log.info("User {} is updated", user.getId());
            userRepository.save(user);
            return user.getId();
        }
        return userId;
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {
        log.info("User {} is deleted", userId);
        userRepository.deleteById(userId);
    }


    @Override
    @Transactional
    public List<UserResponse> getAllUsers() {
        log.info("Returning a list of users");

        List<User> users = userRepository.findAll();

        return users.stream().map(this::mapToUserResponseWithPosts).toList();
    }


    @Transactional
    public UserResponse getUserById(String userId) {
        log.info("Retrieving user by ID: {}", userId);

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return mapToUserResponse(user);
        } else {
            // You may choose to throw an exception or return null or an appropriate response
            return null; // or throw a custom exception
        }
    }

    private UserResponse mapToUserResponseWithPosts(User user) {
        List <PostResponse> posts = getPostByUserId(user.getId());
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .posts(posts)
                .build();
    }

    private List<PostResponse> getPostByUserId(String id) {
        return webClientBuilder.build().get().uri(postServiceUrl + "/{userId}", id)
                .retrieve()
                .bodyToFlux(PostResponse.class)
                .collectList()
                .block();
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

}
