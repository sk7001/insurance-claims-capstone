package com.edutech.insurance_claims_processing_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.insurance_claims_processing_system.entity.Claim;
import com.edutech.insurance_claims_processing_system.entity.Investigation;
import com.edutech.insurance_claims_processing_system.entity.Investigator;
import com.edutech.insurance_claims_processing_system.repository.ClaimRepository;
import com.edutech.insurance_claims_processing_system.repository.InvestigationRepository;
import com.edutech.insurance_claims_processing_system.repository.InvestigatorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class InvestigationService {
 private final InvestigationRepository investigationRepository;
    private final InvestigatorRepository investigatorRepository;
    private final ClaimRepository claimRepository;

    public InvestigationService(InvestigationRepository investigationRepository,
                                InvestigatorRepository investigatorRepository,
                                ClaimRepository claimRepository) {
        this.investigationRepository = investigationRepository;
        this.investigatorRepository = investigatorRepository;
        this.claimRepository = claimRepository;
    }

    // Create investigation for a claim
    public Investigation createInvestigation(Long claimId, Long investigatorId, Investigation investigation) {
        Claim claim = claimRepository.findById(claimId).orElse(null);
        Investigator investigator = investigatorRepository.findById(investigatorId).orElse(null);

        if (claim == null || investigator == null) return null;

        investigation.setClaim(claim);
        investigation.setInvestigator(investigator);

        return investigationRepository.save(investigation);
    }

    // Get investigation by claim
    public Investigation getInvestigationByClaim(Long claimId) {
        return investigationRepository.findByClaimId(claimId);
    }

    // Update investigation result
    public Investigation updateInvestigation(Long investigationId, Investigation updated) {
        Investigation existing = investigationRepository.findById(investigationId).orElse(null);

        if (existing == null) return null;

        existing.setReportDetails(updated.getReportDetails());
        existing.setResult(updated.getResult());

        return investigationRepository.save(existing);
    }

    //implement required code here

}
