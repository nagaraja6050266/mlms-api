package com.example.mlms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "certificates")
@Getter
@Setter
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer certificateId;

    @ManyToOne
    @JoinColumn(name = "certificate_type_id", referencedColumnName = "certificate_type_id") // Ensure correct column mapping
    private CertificateType certificateType;

    @Column(nullable = false)
    private String issuedDate;

    @Column(nullable = false)
    private String filePath;

    private String expiryDate;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Transient // This field will not be persisted in the database
    private Object certificateDetails;

    public Certificate() {
    }

    public Certificate(Integer certificateId, CertificateType certificateType, String issuedDate, String filePath) {
        this.certificateId = certificateId;
        this.certificateType = certificateType;
        this.issuedDate = issuedDate;
        this.filePath = filePath;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public CertificateType getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(CertificateType certificateType) {
        this.certificateType = certificateType;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Object getCertificateDetails() {
        return certificateDetails;
    }

    public void setCertificateDetails(Object certificateDetails) {
        this.certificateDetails = certificateDetails;
    }
}
