package com.spendiq.spendiq.repository;

import com.spendiq.spendiq.model.Transaction;
import com.spendiq.spendiq.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUser(User user);
}