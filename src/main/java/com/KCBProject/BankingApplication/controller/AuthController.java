//Authored by Christal Cain and Brandon Lloyd

package com.KCBProject.BankingApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.Optional;

import com.KCBProject.BankingApplication.service.EmployeeService;
import com.KCBProject.BankingApplication.entity.User;
import com.KCBProject.BankingApplication.service.UserService;

class LoginRequest {
    private String username;
    private String password;

    public LoginRequest() {}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("LOGIN DEBUG - username=" + loginRequest.getUsername() + ", password=" + loginRequest.getPassword());

        Optional<User> user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        System.out.println("LOGIN RESULT - user present: " + user.isPresent());

        if(user.isPresent()) {
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful!",
                    "userId", user.get().getUserId()
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of(
                    "message", "Invalid username or password."
            ));
        }
    }

    @PostMapping("/employee-login")
    public ResponseEntity<Map<String, Object>> employeeLogin(@RequestBody LoginRequest loginRequest) {
        System.out.println("EMPLOYEE LOGIN DEBUG - username=" + loginRequest.getUsername() + ", password=" + loginRequest.getPassword());

        var employeeOpt = employeeService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        System.out.println("EMPLOYEE LOGIN RESULT - employee present: " + employeeOpt.isPresent());

        if (employeeOpt.isPresent()) {
            var employee = employeeOpt.get();

            return ResponseEntity.ok(Map.of(
                    "message", "Employee login successful.",
                    "employeeId", employee.getEmployeeId(),
                    "position", employee.getPosition()
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of(
                    "message", "Invalid employee credentials."
            ));
        }
    }
}
