//Author: Brandon Lloyd

package com.KCBProject.BankingApplication.controller;
import com.KCBProject.BankingApplication.entity.Account;
import com.KCBProject.BankingApplication.service.*;
import com.KCBProject.BankingApplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminWebController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BankService bankService;

    // Admin dashboard main page
    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin-dashboard";
    }

    // View all customers
    @GetMapping("/customers")
    public String viewCustomers(Model model) {
        model.addAttribute("customers", userService.getAllUsers());
        return "admin-customers";
    }

    // View all employees
    @GetMapping("/employees")
    public String viewEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "admin-employees";
    }

    // View all accounts
    @GetMapping("/accounts")
    public String viewAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "admin-accounts";
    }

    // View all transactions
    @GetMapping("/transactions")
    public String viewTransactions(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "admin-transactions";
    }

    //search users
    @GetMapping("/search")
    public String searchUsersForm() {
        return "admin-search-users";
    }
    @PostMapping("/search")
    public String processSearch(@RequestParam String lastName, Model model) {
        List<User> users = userService.findByLastName(lastName);
        model.addAttribute("results", users);
        return "admin-search-users";
    }

    //add accounts
    @GetMapping("/account/add")
    public String showAddAccountForm(Model model) {
        model.addAttribute("account", new Account());
        return "admin-add-account";
    }
    @PostMapping("/account/add")
    public String processAddAccount(
            @RequestParam Long userId,
            @RequestParam String accountType,
            @RequestParam BigDecimal balance,
            Model model) {
        try{
            accountService.createAccount(userId, accountType, balance);
            model.addAttribute("sucess", "Account successfully created.");
        } catch (Exception e) {
            model.addAttribute("error", "Error creating account: " + e.getMessage());
        }
        model.addAttribute("account", new Account());
        return "admin-add-account";
    }


    //delete accounts
    @GetMapping("/account/delete")
    public String showDeleteAccountForm() {
        return "admin-delete-account";
    }
    @PostMapping("/account/delete")
    public String processDeleteAccount(@RequestParam Long accountId, Model model) {
        try {
            accountService.deleteAccount(accountId);
            model.addAttribute("success", "Account deleted sucessfully.");
        } catch (Exception e) {
            model.addAttribute("error", "Error deleting account: " + e.getMessage());
        }
        return "admin-delete-account";
    }

    //view all banks
    @GetMapping("/banks")
    public String viewAllBanks(Model model) {
        model.addAttribute("banks", bankService.getAllBanks());
        return "admin-view-banks";
    }

    //add user/customer
    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin-adduser";
    }
    @PostMapping("/user/create")
    public String processAddUser(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String birthday,
            Model model) {

        try {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setPassword(password);
            user.setDateCreated(new java.util.Date());
            user.setBirthday(java.sql.Date.valueOf(birthday));

            userService.save(user);

            model.addAttribute("message", "User created successfully.");
            model.addAttribute("user", new User());

        } catch (Exception e) {
            model.addAttribute("message", "Error creating user: " + e.getMessage());
        }

        return "admin-adduser";
    }


}