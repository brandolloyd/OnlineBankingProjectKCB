package com.KCBProject.BankingApplication.controller;
import com.KCBProject.BankingApplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.KCBProject.BankingApplication.repository.UserRepository;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")

public class AdminController {
    @Autowired
    private UserRepository userRepository;

    //List all users in system, admin feature
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Search users by last name
    @GetMapping("/users/by-lastname")
    public List<User> getUsersByLastName(@RequestParam String lastName) {
        return userRepository.findByLastNameContainingIgnoreCase(lastName);

    }

    //Delete users
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return ResponseEntity.ok(Map.of("message", "User " + userId + " deleted successfully."));
        } else {
            return ResponseEntity.status(404).body(Map.of("message", "User not found."));
        }
    }
}