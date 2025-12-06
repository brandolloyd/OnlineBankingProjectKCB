//Entity logic/tables created by Brandon Lloyd/Kyra Currence

package com.KCBProject.BankingApplication.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.KCBProject.BankingApplication.entity.User;

@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNTID")
    private Long accountId;

    @Column(name = "USERID")
    private Long userId; //Foreign key

    @Column(name = "ACCOUNTTYPE")
    private String accountType;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "DATEOPENED")
    private LocalDateTime dateOpened;

    @ManyToOne
    @JoinColumn(name = "USERID", insertable = false, updatable = false)
    private User user;

    //getters and setters
    //AcountId
    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    //UserId
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    //AccountType
    public String getAccountType(){
        return accountType;
    }
    public void setAccountType (String accountType) {
        this.accountType = accountType;
    }
    //Balance
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    //DateOpened
    public LocalDateTime getDateOpened() {
        return dateOpened;
    }
    public void setDateOpened (LocalDateTime dateOpened) {
        this.dateOpened = dateOpened;
    }
}
