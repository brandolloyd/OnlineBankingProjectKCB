package com.KCBProject.BankingApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KCBProject.BankingApplication.entity.Account;
import com.KCBProject.BankingApplication.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    //Retrieves all accounts belonging to a specific user
    public Iterable<Account> findAccountsByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    public Account findAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
