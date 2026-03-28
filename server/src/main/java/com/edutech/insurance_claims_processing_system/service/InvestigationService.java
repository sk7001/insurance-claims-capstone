package com.edutech.insurance_claims_processing_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.insurance_claims_processing_system.entity.Investigation;
import com.edutech.insurance_claims_processing_system.repository.InvestigationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigationService {
    
    @Autowired
    private InvestigationRepository investigationRepository;
    
    public Investigation createInvestigation(Investigation investigation) {
        if (investigation.getStatus() == null) investigation.setStatus("PENDING");
        return investigationRepository.save(investigation);
    }
    
    public Investigation updateInvestigation(Long id, Investigation investigationDetails) {
        Optional<Investigation> optionalInvestigation = investigationRepository.findById(id);
        if (optionalInvestigation.isPresent()) {
            Investigation investigation = optionalInvestigation.get();
            if (investigationDetails.getReport() != null) investigation.setReport(investigationDetails.getReport());
            if (investigationDetails.getStatus() != null) investigation.setStatus(investigationDetails.getStatus());
            return investigationRepository.save(investigation);
        }
        throw new RuntimeException("Investigation not found: " + id);
    }
    
    public List<Investigation> getAllInvestigations() {
        return investigationRepository.findAll();
    }
}