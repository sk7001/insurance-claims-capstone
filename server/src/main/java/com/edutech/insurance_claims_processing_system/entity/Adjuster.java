package com.edutech.insurance_claims_processing_system.entity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Adjuster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adjusterName;
    private String specialization;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "adjuster")
    private List<Claim> assignedClaims;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdjusterName() {
        return adjusterName;
    }

    public void setAdjusterName(String adjusterName) {
        this.adjusterName = adjusterName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Claim> getAssignedClaims() {
        return assignedClaims;
    }

    public void setAssignedClaims(List<Claim> assignedClaims) {
        this.assignedClaims = assignedClaims;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}