package com.edutech.insurance_claims_processing_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.insurance_claims_processing_system.entity.Claim;
import com.edutech.insurance_claims_processing_system.entity.Underwriter;
import com.edutech.insurance_claims_processing_system.repository.UnderwriterRepository;
import com.edutech.insurance_claims_processing_system.service.ClaimService;

@RestController
@RequestMapping("/api/adjuster")
public class AdjusterController {

    @Autowired
    private ClaimService claimService;

    @Autowired
    private UnderwriterRepository underwriterRepository;

    // ✅ Update claim
    @PutMapping("/claim/{id}")
    public ResponseEntity<Claim> updateClaim(
            @PathVariable Long id,
            @RequestBody Claim claimDetails) {

        Claim updated = claimService.updateClaim(id, claimDetails);
        return ResponseEntity.ok(updated);
    }

    // ✅ View all claims
    @GetMapping("/claims")
    public List<Claim> getAllClaims() {
        return claimService.getAllClaims();
    }

    // ✅ View all underwriters
    @GetMapping("/underwriters")
    public List<Underwriter> getAllUnderwriters() {
        return underwriterRepository.findAll();
    }

    // ✅ Assign claim to underwriter
    @PutMapping("/claim/{claimId}/assign")
    public ResponseEntity<Claim> assignClaimToUnderwriter(
            @PathVariable Long claimId,
            @RequestParam Long underwriterId) {

        Claim assigned = claimService.assignClaimToUnderwriter(claimId, underwriterId);
        return ResponseEntity.ok(assigned);
    }
}