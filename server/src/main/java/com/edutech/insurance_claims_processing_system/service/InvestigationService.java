package com.edutech.insurance_claims_processing_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.insurance_claims_processing_system.entity.Investigation;
import com.edutech.insurance_claims_processing_system.repository.InvestigationRepository;

@Service
public class InvestigationService {

    @Autowired private InvestigationRepository investigationRepository;

    public Investigation createInvestigation(Investigation i){
        return investigationRepository.save(i);
    }

    public Investigation updateInvestigation(Long id, Investigation d){
        Investigation i = investigationRepository.findById(id).get();
        i.setReport(d.getReport());
        i.setStatus(d.getStatus());
        return investigationRepository.save(i);
    }

    public List<Investigation> getAllInvestigations(){
        return investigationRepository.findAll();
    }
}