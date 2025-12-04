package com.KCBProject.BankingApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import com.KCBProject.BankingApplication.entity.User;
import com.KCBProject.BankingApplication.entity.Account;
import com.KCBProject.BankingApplication.service.UserService;
import com.KCBProject.BankingApplication.service.AccountService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;
    /*
    Handles requests to view the user dashboard
    UserId is passed via a request parameter, but in a real system
    it would be MORE SECURE
     */
    @GetMapping("/user/dashboard")
    public String viewDashboard(HttpSession session, Model model) {

        // 1. Get the logged-in user from the session
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            // No one logged in → send back to login page
            return "redirect:/login";
        }

        // 2. Load this user's accounts using their ID
        Long userId = user.getUserId();
        Iterable<Account> accounts = accountService.findAccountsByUserId(userId);

        // 3. Add data to the model
        model.addAttribute("user", user);
        model.addAttribute("accounts", accounts);

        // 4. Render dashboard
        return "user-dashboard";
    }
    @GetMapping("/user/account/{accountId}")
    public String viewAccountDetails(@PathVariable Long accountId,
                                     HttpSession session,
                                     Model model){
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }

        Iterable<Account> accounts = accountService.findAccountsByUserId(user.getUserId());

        Account selectedAccount = null;
        for (Account acc : accounts) {
            if (acc.getAccountId().equals(accountId)) {
                selectedAccount = acc;
                break;
            }
        }

        if (selectedAccount == null) {
            return "redirect:/user/dashboard";
        }
        model.addAttribute("user", user);
        model.addAttribute("account", selectedAccount);

        return "account-details";
    }

    @GetMapping("/user/account/{accountId}/deposit")
    public String showDepositForm(@PathVariable Long accountId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null){
            return "redirect:/login";
        }

        model.addAttribute("accountId", accountId);
        return "account-deposit";
    }
    @PostMapping("/user/account/{accountId}/deposit")
    public String processDeposit(@PathVariable Long accountId,
                                 @RequestParam double amount,
                                 HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null){
            return "redirect:/login";
        }

        Account account = accountService.findAccountById(accountId);
        if (account == null || !account.getUserId().equals(user.getUserId())) {
            return "redirect:/user/dashboard";
        }

        BigDecimal newBalance = account.getBalance().add(BigDecimal.valueOf(amount));
        account.setBalance(newBalance);
        accountService.save(account);

        return "redirect:/user/account/" + accountId;
    }

    @GetMapping("/api/users/{username}/dashboard")
    public ResponseEntity<?> getUserDashboard(@PathVariable String username) {
        // Look up the user by username
        Optional<User> userOpt = userService.findByUsername(username);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "User not found"));
        }

        User user = userOpt.get();

        // Load this user's accounts
        Iterable<Account> accounts = accountService.findAccountsByUserId(user.getUserId());

        // Build a simple response payload
        Map<String, Object> response = new HashMap<>();
        response.put("fullName", user.getFirstName() + " " + user.getLastName());
        response.put("username", user.getUsername());
        response.put("accounts", accounts);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/account/{accountId}/deposit")
    public String showDepositForm(@PathVariable Long accountId, Model model) {
        model.addAttribute("accountId", accountId);
        return "account-deposit";
    }
}
