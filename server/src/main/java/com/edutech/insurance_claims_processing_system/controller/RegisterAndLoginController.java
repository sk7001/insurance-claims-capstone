package com.edutech.insurance_claims_processing_system.controller;

import com.edutech.insurance_claims_processing_system.dto.LoginRequest;
import com.edutech.insurance_claims_processing_system.dto.LoginResponse;
import com.edutech.insurance_claims_processing_system.entity.User;
import com.edutech.insurance_claims_processing_system.jwt.JwtUtil;
import com.edutech.insurance_claims_processing_system.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class RegisterAndLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        User user = userService.getUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(user.getUsername());

        LoginResponse response = new LoginResponse(
            user.getId(),
            token,
            user.getUsername(),
            user.getEmail(),
            user.getRole()
        );

        return ResponseEntity.ok(response);
    }
}