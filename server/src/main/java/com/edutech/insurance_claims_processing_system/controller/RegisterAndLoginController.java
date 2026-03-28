package com.edutech.insurance_claims_processing_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.edutech.insurance_claims_processing_system.dto.LoginRequest;
import com.edutech.insurance_claims_processing_system.dto.LoginResponse;
import com.edutech.insurance_claims_processing_system.entity.User;
import com.edutech.insurance_claims_processing_system.jwt.JwtUtil;
import com.edutech.insurance_claims_processing_system.service.UserService;

@RestController
@RequestMapping("/api/user")
public class RegisterAndLoginController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public RegisterAndLoginController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
        }
        if (user.getRole() == null) user.setRole("USER");
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
        }
        
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password", e);
        }
        
        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        User foundUser = userService.getUserByUsername(loginRequest.getUsername());
        final String token = jwtUtil.generateToken(loginRequest.getUsername());
        Long userId = foundUser.getId();
        String role = foundUser.getRole();
        
        return ResponseEntity.ok(new LoginResponse(userId, token, foundUser.getUsername(), foundUser.getEmail(), role));
    }
}