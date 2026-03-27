package com.edutech.insurance_claims_processing_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.insurance_claims_processing_system.entity.Claim;
import com.edutech.insurance_claims_processing_system.service.ClaimService;

import java.util.List;

@RestController
@RequestMapping("/api/policyholder")
@CrossOrigin
public class PolicyholderController {

    private final ClaimService claimService;

    public PolicyholderController(ClaimService claimService) {
        this.claimService = claimService;
    }

    // Create claim
    @PostMapping("/claim")
    public Claim createClaim(@RequestParam Long policyholderId,
                             @RequestBody Claim claim) {
        return claimService.createClaim(policyholderId, claim);
    }

    // View claims
    @GetMapping("/claims")
    public List<Claim> viewClaims(@RequestParam Long policyholderId) {
        return claimService.getClaimsByPolicyholder(policyholderId);
    }

    // View claim status
    @GetMapping("/claim-status")
    public List<Claim> getStatus(@RequestParam Long policyholderId) {
        return claimService.getClaimsByPolicyholder(policyholderId);
    }
}