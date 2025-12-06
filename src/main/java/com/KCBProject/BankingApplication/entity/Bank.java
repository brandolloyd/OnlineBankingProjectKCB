//Authored by Kyra Currence

package com.KCBProject.BankingApplication.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name= "BANK")
public class Bank {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="BANKID")
private Long bankId; //PK

@Column(name="NAME")
private String name;

@Column(name="LOCATION")
private String location;

@OneToMany(mappedBy = "bank")
private List<Employee> employees = new ArrayList<>();

//getters and setters
//Bank ID
public Long getBankId(){return bankId;}
public void setBankId(Long bankId) {this.bankId = bankId;}

// Bank Name
public String getName(){return name;}
public void setName(String name) {this.name = name;}

//Bank Location
public String getLocation(){return location;}
public void setLocation(String location) {this.location = location;}

// Employees
public List<Employee> getEmployees() { return employees; }
public void setEmployees(List<Employee> employees) { this.employees = employees; }

}
