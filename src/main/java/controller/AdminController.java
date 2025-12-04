package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")

public class AdminController {
    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //List all users in the system (admin overview)
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //CrudRepository returns iterable
        return users;
    }

    //Find user by username.
    @GetMapping("/users/by-username")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        return userOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    //Create a new user (admin-created account)
    // JSON body should contain: firstName, LastName, username, password, birthday, dateCreated.

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User saved = userRepository.save(newUser);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Delete a user by id.
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
