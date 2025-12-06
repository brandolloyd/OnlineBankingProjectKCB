package com.KCBProject.BankingApplication.service;

import com.KCBProject.BankingApplication.entity.Employee;
import org.springframework.stereotype.Service;
import com.KCBProject.BankingApplication.repository.EmployeeRepository;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> authenticate(String username, String password) {
        Optional<Employee> employeeOpt = employeeRepository.findByUsername(username);

        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();

            if (employee.getPassword().equals(password)) {
                return Optional.of(employee);
            }
        }
        return Optional.empty();
    }
    public Optional<Employee> findByUsernameAndPassword(String username, String password) {
        return employeeRepository.findByUsernameAndPassword(username, password);
    }
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
