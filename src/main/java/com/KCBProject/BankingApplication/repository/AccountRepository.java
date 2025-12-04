package com.KCBProject.BankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.KCBProject.BankingApplication.entity.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //service calls on this
    List<Account> findByUserId(Long userId);
}
