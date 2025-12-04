package com.KCBProject.BankingApplication.controller;

import com.KCBProject.BankingApplication.entity.User;
import com.KCBProject.BankingApplication.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class WebController {

    private final UserService userService;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login"; // renders login.html
    }
    @PostMapping("/login")
    public String processLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        System.out.println("WEB LOGIN username=" + username + ", password=" + password);

        // Use your existing service method
        Optional<User> userOpt = userService.authenticateUser(username, password);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // store the logged-in user in the session
            session.setAttribute("currentUser", user);
            // go to the user dashboard
            return "redirect:/user/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password.");
            // redisplay the login page with error
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();          // clear all session data
        return "redirect:/login";      // send user back to the login page
    }
}