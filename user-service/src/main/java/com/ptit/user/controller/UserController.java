package com.ptit.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ptit.user.entity.User;
import com.ptit.user.exception.ResourceNotFoundException;
import com.ptit.user.repository.UserRepository;

import javax.validation.Valid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentScan("com.ptit.user")
@RestController
@RequestMapping("/v1") 
public class UserController {
	
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
	@Autowired
	private Environment env;

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUsersById(@PathVariable(value = "id") String userId)
      throws ResourceNotFoundException {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
    return ResponseEntity.ok().body(user);
  }

  @PostMapping("/users")
  public User createUser(@Valid @RequestBody User user) {
    return userRepository.save(user);
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(
      @PathVariable(value = "id") String userId, @Valid @RequestBody @RequestParam User userDetails)
      throws ResourceNotFoundException {

    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

    user.setEmail(userDetails.getEmail());
    user.setName(userDetails.getName());
    user.setAddress(userDetails.getAddress());
    final User updatedUser = userRepository.save(user);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/user/{id}")
  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") String userId) throws Exception {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

    userRepository.delete(user);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}