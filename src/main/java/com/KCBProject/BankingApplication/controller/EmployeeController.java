//Authored by Christal Cain and Brandon Lloyd

package com.KCBProject.BankingApplication.controller;

import com.KCBProject.BankingApplication.entity.Account;
import com.KCBProject.BankingApplication.entity.Employee;
import com.KCBProject.BankingApplication.service.EmployeeService;
import com.KCBProject.BankingApplication.service.TransactionService;
import com.KCBProject.BankingApplication.service.UserService;
import com.KCBProject.BankingApplication.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final UserService userService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    public EmployeeController(EmployeeService employeeService,
                              UserService userService,
                              AccountService accountService,
                              TransactionService transactionService) {
        this.employeeService = employeeService;
        this.userService = userService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/login")
    public String showEmployeeLoginForm() {
        return "employee-login";
    }

    @PostMapping("/login")
    public String loginEmployee(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {
        Optional<Employee> employee = employeeService.findByUsernameAndPassword(username, password);

        if (employee.isPresent()) {
            Employee emp = employee.get();
            session.setAttribute("employeeId", emp.getEmployeeId());

            String position = emp.getPosition();
            if (position != null && (position.equalsIgnoreCase("ADMIN") || position.equalsIgnoreCase("MANAGER"))) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/employee/dashboard";
            }
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "employee-login";
        }
    }
    @GetMapping("/dashboard")
    public String employeeDashboard(HttpSession session, Model model) {
        Object employeeId = session.getAttribute("employeeId");

        if (employeeId == null) {
            return "redirect:/employee/login";
        }

        Employee employee = employeeService.findById((Long) employeeId).orElse(null);
        model.addAttribute("employee", employee);

        return "employee-dashboard";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/employee/login";
    }
    @GetMapping("/customers")
    public String viewAllCustomers(HttpSession session, Model model) {
        Object employeeId = session.getAttribute("employeeId");
        if (employeeId == null){
            return "redirect:/employee/login";
        }
        model.addAttribute("customers", userService.findAll());
        return "employee-customers";
    }

    @GetMapping("/customer/accounts")
    public String viewCustomerAccounts(@RequestParam Long customerId,
                                       HttpSession session,
                                       Model model) {
        Object employeeId = session.getAttribute("employeeId");
        if (employeeId == null) {
            return "redirect:/employee/login";
        }

        //load customer
        var customer = userService.findById(customerId).orElse(null);
        model.addAttribute("customer", customer);

        //Load customer accounts
        var accounts = customer != null ? customer.getAccounts() : null;
        model.addAttribute("accounts", accounts);

        return "employee-customer-accounts";
    }
    @GetMapping("/accounts")
    public String viewAllAccounts(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "employee-all-accounts";
    }

    @GetMapping("/transactions")
    public String viewAllTransactions(HttpSession session, Model model) {
        Object employeeId = session.getAttribute("employeeId");
        if (employeeId == null) {
            return "redirect:/employee/login";
        }

        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "employee-all-transactions";
    }
}