package com.edutech.insurance_claims_processing_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edutech.insurance_claims_processing_system.entity.Adjuster;
import com.edutech.insurance_claims_processing_system.entity.Investigator;
import com.edutech.insurance_claims_processing_system.entity.Policyholder;
import com.edutech.insurance_claims_processing_system.entity.Underwriter;
import com.edutech.insurance_claims_processing_system.entity.User;
import com.edutech.insurance_claims_processing_system.repository.AdjusterRepository;
import com.edutech.insurance_claims_processing_system.repository.InvestigatorRepository;
import com.edutech.insurance_claims_processing_system.repository.PolicyholderRepository;
import com.edutech.insurance_claims_processing_system.repository.UnderwriterRepository;
import com.edutech.insurance_claims_processing_system.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired private UserRepository userRepository;
    @Autowired private AdjusterRepository adjusterRepository;
    @Autowired private InvestigatorRepository investigatorRepository;
    @Autowired private PolicyholderRepository policyholderRepository;
    @Autowired private UnderwriterRepository underwriterRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    
    public User registerUser(User userDetails) {
        User user = createUserInstance(userDetails.getRole());
        copyUserProperties(userDetails, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return saveUserByRole(user);
    }
    
    private User createUserInstance(String role) {
        switch (role.toUpperCase()) {
            case "POLICYHOLDER": return new Policyholder();
            case "ADJUSTER": return new Adjuster();
            case "INVESTIGATOR": return new Investigator();
            case "UNDERWRITER": return new Underwriter();
            default: return new User();
        }
    }
    
    private void copyUserProperties(User source, User target) {
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setRole(source.getRole());
    }
    
    private User saveUserByRole(User user) {
        switch (user.getRole().toUpperCase()) {
            case "POLICYHOLDER": return policyholderRepository.save((Policyholder) user);
            case "ADJUSTER": return adjusterRepository.save((Adjuster) user);
            case "INVESTIGATOR": return investigatorRepository.save((Investigator) user);
            case "UNDERWRITER": return underwriterRepository.save((Underwriter) user);
            default: return userRepository.save(user);
        }
    }
    
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> 
            new UsernameNotFoundException("User not found: " + username));
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities("ROLE_" + user.getRole().toUpperCase())
            .accountExpired(false).accountLocked(false)
            .credentialsExpired(false).disabled(false)
            .build();
    }
}