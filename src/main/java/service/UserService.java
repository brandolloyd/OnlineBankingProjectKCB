package service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import entity.User;
import repository.UserRepository;

//@Service tells Spring this class uses business logic
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    //Authentication process, check username and password against database
    public Optional<User> authenticate(String username, String password) {
        // 1. Search database for user using username
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if(user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }
}
