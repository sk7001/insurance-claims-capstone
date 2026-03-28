package com.edutech.insurance_claims_processing_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.insurance_claims_processing_system.entity.Claim;
import com.edutech.insurance_claims_processing_system.entity.Policyholder;
import com.edutech.insurance_claims_processing_system.entity.Underwriter;
import com.edutech.insurance_claims_processing_system.repository.ClaimRepository;
import com.edutech.insurance_claims_processing_system.repository.PolicyholderRepository;
import com.edutech.insurance_claims_processing_system.repository.UnderwriterRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private PolicyholderRepository policyholderRepository;
    @Autowired
    private UnderwriterRepository underwriterRepository;

    public Claim createClaim(Claim claim) {
        if (claim.getDate() == null)
            claim.setDate(new Date());
        if (claim.getStatus() == null)
            claim.setStatus("PENDING");
        return claimRepository.save(claim);
    }

    public Claim updateClaim(Long id, Claim claimDetails) {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();
            if (claimDetails.getDescription() != null)
                claim.setDescription(claimDetails.getDescription());
            if (claimDetails.getStatus() != null)
                claim.setStatus(claimDetails.getStatus());
            return claimRepository.save(claim);
        }
        throw new RuntimeException("Claim not found: " + id);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public Claim submitClaim(Long policyholderId, Claim claim) {
        Policyholder policyholder = policyholderRepository.findById(policyholderId)
                .orElseThrow(() -> new RuntimeException("Policyholder not found: " + policyholderId));
        claim.setPolicyholder(policyholder);
        return createClaim(claim);
    }

    public List<Claim> getClaimsByPolicyholder(Long policyholderId) {
        Policyholder policyholder = policyholderRepository.findById(policyholderId)
                .orElseThrow(() -> new RuntimeException("Policyholder not found"));
        return claimRepository.findByPolicyholder(policyholder);
    }

    public Claim reviewClaim(Long id, String status) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found: " + id));
        claim.setStatus(status);
        return claimRepository.save(claim);
    }

    public List<Claim> getClaimsForReview(Long underwriterId) {
        Underwriter underwriter = underwriterRepository.findById(underwriterId)
                .orElseThrow(() -> new RuntimeException("Underwriter not found"));
        return claimRepository.findByUnderwriter(underwriter);
    }

    public Claim assignClaimToUnderwriter(Long claimId, Long underwriterId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
        Underwriter underwriter = underwriterRepository.findById(underwriterId)
                .orElseThrow(() -> new RuntimeException("Underwriter not found"));
        claim.setUnderwriter(underwriter);
        if (claim.getDescription() == null || claim.getDescription().trim().isEmpty()) {
            claim.setDescription("Claim assigned to underwriter");
        }
        return claimRepository.save(claim);
    }
}