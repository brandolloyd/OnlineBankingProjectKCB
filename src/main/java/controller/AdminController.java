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
}
