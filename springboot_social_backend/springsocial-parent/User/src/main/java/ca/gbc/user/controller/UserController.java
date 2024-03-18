package ca.gbc.user.controller;

import ca.gbc.user.dto.UserRequest;
import ca.gbc.user.dto.UserResponse;
import ca.gbc.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") String userId) {
        UserResponse user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping({"/{userId}"})
    public ResponseEntity<?> updateUser(@PathVariable("userId") String userId,
                                        @RequestBody UserRequest userRequest) {
        String updatedUserId = userService.updateUser(userId, userRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/user/" + updatedUserId);

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    //http://localhost:8080/api/user/
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
