package com.edutech.insurance_claims_processing_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.insurance_claims_processing_system.entity.Adjuster;
import com.edutech.insurance_claims_processing_system.entity.Claim;
import com.edutech.insurance_claims_processing_system.entity.Policyholder;
import com.edutech.insurance_claims_processing_system.entity.Underwriter;
import com.edutech.insurance_claims_processing_system.repository.AdjusterRepository;
import com.edutech.insurance_claims_processing_system.repository.ClaimRepository;
import com.edutech.insurance_claims_processing_system.repository.PolicyholderRepository;
import com.edutech.insurance_claims_processing_system.repository.UnderwriterRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClaimService {
private final ClaimRepository claimRepository;
    private final PolicyholderRepository policyholderRepository;
    private final AdjusterRepository adjusterRepository;

    public ClaimService(ClaimRepository claimRepository,
                        PolicyholderRepository policyholderRepository,
                        AdjusterRepository adjusterRepository) {
        this.claimRepository = claimRepository;
        this.policyholderRepository = policyholderRepository;
        this.adjusterRepository = adjusterRepository;
    }

    // Create a new Claim
    public Claim createClaim(Long policyholderId, Claim claim) {
        Policyholder ph = policyholderRepository.findById(policyholderId).orElse(null);
        if (ph == null) return null;

        claim.setPolicyholder(ph);
        claim.setStatus("PENDING");
        return claimRepository.save(claim);
    }

    // List claims of a policyholder
    public List<Claim> getClaimsByPolicyholder(Long policyholderId) {
        return claimRepository.findByPolicyholderId(policyholderId);
    }

    // Assign adjuster to claim
    public Claim assignAdjuster(Long claimId, Long adjusterId) {
        Claim claim = claimRepository.findById(claimId).orElse(null);
        Adjuster adjuster = adjusterRepository.findById(adjusterId).orElse(null);

        if (claim == null || adjuster == null) return null;

        claim.setAdjuster(adjuster);
        return claimRepository.save(claim);
    }

    // List claims assigned to an Adjuster
    public List<Claim> getClaimsByAdjuster(Long adjusterId) {
        return claimRepository.findByAdjusterId(adjusterId);
    }

    // Update claim status
    public Claim updateClaimStatus(Long claimId, String status) {
        Claim claim = claimRepository.findById(claimId).orElse(null);
        if (claim == null) return null;

        claim.setStatus(status);
        return claimRepository.save(claim);
    }

    //implement required code here
}
