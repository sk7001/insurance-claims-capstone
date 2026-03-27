package com.edutech.insurance_claims_processing_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String status;
    private LocalDate date;

    private Long policyholderId;
    private Long adjusterId;

    public Claim() {}

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public LocalDate getDate() { return date; }

    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
    public void setDate(LocalDate date) { this.date = date; }

    public Long getPolicyholderId() { return policyholderId; }
    public void setPolicyholderId(Long policyholderId) { this.policyholderId = policyholderId; }

    public Long getAdjusterId() { return adjusterId; }
    public void setAdjusterId(Long adjusterId) { this.adjusterId = adjusterId; }
}