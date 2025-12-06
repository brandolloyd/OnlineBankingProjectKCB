//Authored by Brandon Lloyd/Kyra Currence

package com.KCBProject.BankingApplication.entity;

import com.KCBProject.BankingApplication.entity.Bank;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEEID")
    private Long employeeId;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "STARTDATE")
    private Date startDate;

    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToOne
    @JoinColumn(name = "BANKID")
    private Bank bank;

    //getters and setters
    //employeeid
    public Long getEmployeeId() {return employeeId;}
    public void setEmployeeId(Long employeeId){this.employeeId = employeeId;}

    //FirstName
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    //LastName
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    //Position
    public String getPosition() {return position;}
    public void setPosition(String position) {this.position = position;}

    //startDate
    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    //birthday
    public Date getBirthday() {return birthday;}
    public void setBirthday(Date birthday) {this.birthday = birthday;}

    //username
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    //password
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    //bank
    public Bank getBank() {return bank;}
    public void setBank(Bank bank) {this.bank = bank;}
}
