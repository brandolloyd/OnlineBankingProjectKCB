package controller;
import entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")

public class AdminController {
    private final UserRepository userRepository;

    //Constructor injection
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //List all user
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    //Search user by account number (Admin panel: search by account number)
    @GetMapping("/users/by-account/{accountNumber}")
    public ResponseEntity<User> getUserByAccountNumber(@PathVariable String accountNumber) {
        Optional<User> userOpt = userRepository.findByAccountNumber(accountNumber);

        return userOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Search users by last name (Admin panel: search by last name)
    @GetMapping("/users/by-lastname")
}
