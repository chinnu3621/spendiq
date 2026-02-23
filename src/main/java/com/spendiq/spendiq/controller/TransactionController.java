package com.spendiq.spendiq.controller;

import com.spendiq.spendiq.model.Transaction;
import com.spendiq.spendiq.model.User;
import com.spendiq.spendiq.repository.TransactionRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionRepository repository;

    @PostMapping
    public Transaction add(@RequestBody Transaction transaction, HttpSession session) {

        User user = (User) session.getAttribute("user");
        transaction.setUser(user);

        return repository.save(transaction);
    }

    @GetMapping
    public List<Transaction> getAll(HttpSession session) {

        User user = (User) session.getAttribute("user");
        return repository.findByUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}