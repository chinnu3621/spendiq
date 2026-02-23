package com.spendiq.spendiq.controller;

import com.spendiq.spendiq.model.User;
import com.spendiq.spendiq.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "User already exists";
        }

        // SAVE PASSWORD AS NORMAL TEXT (temporary)
        userRepository.save(user);

        return "Registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) {

        User existing = userRepository.findByUsername(user.getUsername());

        if (existing != null &&
                existing.getPassword().equals(user.getPassword())) {

            session.setAttribute("user", existing);
            return "Login successful";
        }

        return "Invalid username or password";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out";
    }
}