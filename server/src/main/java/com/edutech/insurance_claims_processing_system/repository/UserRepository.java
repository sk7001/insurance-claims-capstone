package com.edutech.insurance_claims_processing_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.insurance_claims_processing_system.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}