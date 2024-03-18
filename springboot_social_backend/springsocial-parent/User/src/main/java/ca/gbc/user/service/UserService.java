package ca.gbc.user.service;

import ca.gbc.user.dto.UserRequest;
import ca.gbc.user.dto.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void createUser(UserRequest userRequest);
    String updateUser(String userId, UserRequest userRequest);
    void deleteUser(String userId);
    List<UserResponse> getAllUsers();

}
