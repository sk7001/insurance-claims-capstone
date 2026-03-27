package com.edutech.insurance_claims_processing_system.controller;

import com.edutech.insurance_claims_processing_system.entity.Claim;
import com.edutech.insurance_claims_processing_system.service.ClaimService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/underwriter")
public class UnderwriterController {

    @Autowired
    private ClaimService claimService;

    @PutMapping("/claim/{id}/review")
    public ResponseEntity<Claim> reviewClaim(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
            claimService.reviewClaim(id, status)
        );
    }

    @GetMapping("/claims")
    public ResponseEntity<List<Claim>> getClaimsForReview(
            @RequestParam Long underwriterId) {

        return ResponseEntity.ok(
            claimService.getClaimsForReview(underwriterId)
        );
    }
}