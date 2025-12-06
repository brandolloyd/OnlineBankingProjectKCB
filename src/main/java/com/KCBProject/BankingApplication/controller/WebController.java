//Authored by Brandon Lloyd

package com.KCBProject.BankingApplication.controller;

import com.KCBProject.BankingApplication.entity.User;
import com.KCBProject.BankingApplication.entity.Account;
import com.KCBProject.BankingApplication.service.UserService;
import com.KCBProject.BankingApplication.service.AccountService;
import com.KCBProject.BankingApplication.service.EmployeeService;
import com.KCBProject.BankingApplication.service.TransactionService;
import com.KCBProject.BankingApplication.service.BankService;
import java.math.BigDecimal;
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
    private final AccountService accountService;
    private final EmployeeService employeeService;
    private final TransactionService transactionService;
    private final BankService bankService;

    @Autowired
    public WebController(
            UserService userService,
            AccountService accountService,
            EmployeeService employeeService,
            TransactionService transactionService,
            BankService bankService
    ) {
        this.userService = userService;
        this.accountService = accountService;
        this.employeeService = employeeService;
        this.transactionService = transactionService;
        this.bankService = bankService;
    }

    @GetMapping("/")
    public String home() {
        return "homepage";
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