package com.KCBProject.BankingApplication.repository;

import org.springframework.data.repository.CrudRepository;
import com.KCBProject.BankingApplication.entity.Transaction;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByAccountIdOrderByTransactionDateDesc(Long accountId);
}
