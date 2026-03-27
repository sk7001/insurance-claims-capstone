package com.edutech.insurance_claims_processing_system.controller;

import com.edutech.insurance_claims_processing_system.entity.Investigation;
import com.edutech.insurance_claims_processing_system.service.InvestigationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investigator")
public class InvestigatorController {

    @Autowired
    private InvestigationService investigationService;

    @PostMapping("/investigation")
    public ResponseEntity<Investigation> createInvestigation(
            @RequestBody Investigation investigation) {

        return ResponseEntity.ok(
            investigationService.createInvestigation(investigation)
        );
    }

    @PutMapping("/investigation/{id}")
    public ResponseEntity<Investigation> updateInvestigation(
            @PathVariable Long id,
            @RequestBody Investigation investigationDetails) {

        return ResponseEntity.ok(
            investigationService.updateInvestigation(id, investigationDetails)
        );
    }

    @GetMapping("/investigations")
    public List<Investigation> getAllInvestigations() {
        return investigationService.getAllInvestigations();
    }
}
