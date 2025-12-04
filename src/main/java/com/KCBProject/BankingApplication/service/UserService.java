package com.KCBProject.BankingApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.KCBProject.BankingApplication.entity.User;
import com.KCBProject.BankingApplication.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Authentication helper used by WebController
    public Optional<User> authenticateUser(String username, String password) {
        System.out.println("AUTH DEBUG (UserService.authenticateUser): username=" + username + ", password=" + password);

        userRepository.findAll().forEach(u ->
                System.out.println("ROW IN DB (UserService): " + u.getUsername() + " / " + u.getPassword())
        );

        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);

        System.out.println("AUTH RESULT (UserService.authenticateUser) - user present: " + userOptional.isPresent());

        return userOptional;
    }

    // WebController uses this to load the full user object after login
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    // Debug version used earlier — still okay to keep
    public Optional<User> authenticate(String username, String password) {
        System.out.println("AUTH DEBUG: username=" + username + ", password=" + password);

        userRepository.findAll().forEach(u ->
                System.out.println("ROW IN DB: " + u.getUsername() + " / " + u.getPassword())
        );

        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);

        System.out.println("AUTH RESULT - user present: " + userOptional.isPresent());

        return userOptional;
    }
}