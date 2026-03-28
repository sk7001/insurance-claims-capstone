package com.edutech.insurance_claims_processing_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.insurance_claims_processing_system.entity.Investigation;
import com.edutech.insurance_claims_processing_system.service.InvestigationService;

import java.util.List;

@RestController
@RequestMapping("/api/investigator")
public class InvestigatorController {

    @Autowired
    private InvestigationService investigationService;

    @PostMapping("/investigation")
    public ResponseEntity<Investigation> createInvestigation(@RequestBody Investigation investigation) {
        Investigation saved = investigationService.createInvestigation(investigation);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/investigation/{id}")
    public ResponseEntity<Investigation> updateInvestigation(@PathVariable Long id,
            @RequestBody Investigation details) {
        Investigation updated = investigationService.updateInvestigation(id, details);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/investigations")
    public ResponseEntity<List<Investigation>> getAllInvestigations() { // Add ResponseEntity
        List<Investigation> investigations = investigationService.getAllInvestigations();
        return ResponseEntity.ok(investigations);
    }
}