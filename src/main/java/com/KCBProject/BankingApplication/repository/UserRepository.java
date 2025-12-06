package com.KCBProject.BankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.KCBProject.BankingApplication.entity.User;
import java.util.List;
import java.util.Optional;
// Error handling utility for results

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    List<User> findByLastNameContainingIgnoreCase(String lastName);

    List<User> findAll();

    List<User> findByLastName(String lastName);
}
