package com.KCBProject.BankingApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KCBProject.BankingApplication.entity.Transaction;
import com.KCBProject.BankingApplication.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountIdOrderByTransactionDateDesc(accountId);
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> results = new ArrayList<>();
        transactionRepository.findAll().forEach(results::add);
        return results;
    }

    public void recordTransaction(Long accountId,
                                  BigDecimal amount,
                                  String type,
                                  String description) {
        Transaction t = new Transaction();

        // Your setter is setAccountid
        t.setAccountId(accountId);

        // BigDecimal conversion
        t.setAmount(amount);

        t.setTransactionType(type);
        t.setDescription(description);

        // You use java.util.Date, not LocalDateTime
        t.setTransactionDate(new java.util.Date());

        transactionRepository.save(t);
    }
}
