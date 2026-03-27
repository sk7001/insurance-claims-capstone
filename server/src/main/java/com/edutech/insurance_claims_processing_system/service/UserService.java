package com.edutech.insurance_claims_processing_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edutech.insurance_claims_processing_system.entity.*;
import com.edutech.insurance_claims_processing_system.repository.*;

import java.util.ArrayList;

@Service
public class UserService {
private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register User
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Login User (basic check, token handled in controller)
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Find user by username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Find user by ID
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    //implement required code here

}