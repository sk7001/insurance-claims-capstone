package com.edutech.insurance_claims_processing_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.insurance_claims_processing_system.entity.Claim;
import com.edutech.insurance_claims_processing_system.service.ClaimService;

import java.util.List;

@RestController
@RequestMapping("/api/policyholder")
public class PolicyholderController {

    @Autowired
    private ClaimService claimService;

    @PostMapping("/claim")
    public ResponseEntity<Claim> submitClaim(@RequestParam Long policyholderId, @RequestBody Claim claim) {
        Claim savedClaim = claimService.submitClaim(policyholderId, claim);
        return ResponseEntity.ok(savedClaim);
    }

    @GetMapping("/claims")
    public ResponseEntity<List<Claim>> getClaims(@RequestParam Long policyholderId) {
        List<Claim> claims = claimService.getClaimsByPolicyholder(policyholderId);
        return ResponseEntity.ok(claims); // Returns proper JSON array
    }
}