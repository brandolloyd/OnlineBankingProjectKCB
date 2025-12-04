package com.KCBProject.BankingApplication.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @Column(name = "TRANSACTIONID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "ACCOUNTID")
    private Long accountId;

    @Column(name = "TRANSACTIONTYPE")
    private String transactionType; //DEPOSIT, WITHDRAWAL, TRANSFER

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TRANSACTIONDATE")
    private Date transactionDate;

    //getters and setters
    //transactionid
    public Long getTransactionId() {return transactionId;}
    public void setTransactionId(Long transactionId) {this.transactionId = transactionId; }

    //accountid
    public Long getAccountId() {return accountId;}
    public void setAccountid(Long accountId) {this.accountId = accountId;}

    //transactiontype
    public String getTransactionType() {return transactionType;}
    public void setTransactionType(String type) {this.transactionType = type;}

    //amount
    public BigDecimal getAmount() {return amount;}
    public void setAmount(BigDecimal amount) {this.amount = amount;}

    //description
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    //date
    public Date getTransactionDate() {return transactionDate;}
    public void setTransactionDate(Date transactionDate) {this.transactionDate = transactionDate;}

}
