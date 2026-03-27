package com.edutech.insurance_claims_processing_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.insurance_claims_processing_system.entity.Claim;
import com.edutech.insurance_claims_processing_system.entity.Policyholder;
import com.edutech.insurance_claims_processing_system.entity.Underwriter;
import com.edutech.insurance_claims_processing_system.repository.ClaimRepository;
import com.edutech.insurance_claims_processing_system.repository.PolicyholderRepository;
import com.edutech.insurance_claims_processing_system.repository.UnderwriterRepository;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private PolicyholderRepository policyholderRepository;

    @Autowired
    private UnderwriterRepository underwriterRepository;

    // ✅ Create claim (generic)
    public Claim createClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    // ✅ Submit claim by policyholder
    public Claim submitClaim(Long pid, Claim claim) {
        claim.setPolicyholder(
                policyholderRepository.findById(pid).orElse(null)
        );
        return claimRepository.save(claim);
    }

    // ✅ Get claims by policyholder
    public List<Claim> getClaimsByPolicyholder(Long pid) {
        Policyholder policyholder =
                policyholderRepository.findById(pid).orElse(null);
        return claimRepository.findByPolicyholder(policyholder);
    }

    // ✅ ✅ MISSING METHOD #1 — FIX
    public Claim updateClaim(Long id, Claim claimDetails) {
        Claim claim = claimRepository.findById(id).orElse(null);

        if (claim != null) {
            claim.setDescription(claimDetails.getDescription());
            claim.setDate(claimDetails.getDate());
            claim.setStatus(claimDetails.getStatus());
            return claimRepository.save(claim);
        }
        return null;
    }

    // ✅ ✅ MISSING METHOD #2 — FIX
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    // ✅ Assign claim to underwriter
    public Claim assignClaimToUnderwriter(Long cid, Long uid) {
        Claim claim = claimRepository.findById(cid).orElse(null);
        Underwriter underwriter =
                underwriterRepository.findById(uid).orElse(null);

        if (claim != null && underwriter != null) {
            claim.setUnderwriter(underwriter);
            return claimRepository.save(claim);
        }
        return null;
    }

    // ✅ Review claim (underwriter)
    public Claim reviewClaim(Long id, String status) {
        Claim claim = claimRepository.findById(id).orElse(null);

        if (claim != null) {
            claim.setStatus(status);
            return claimRepository.save(claim);
        }
        return null;
    }

    // ✅ Get claims for review
    public List<Claim> getClaimsForReview(Long uid) {
        Underwriter underwriter =
                underwriterRepository.findById(uid).orElse(null);
        return claimRepository.findByUnderwriter(underwriter);
    }
}
