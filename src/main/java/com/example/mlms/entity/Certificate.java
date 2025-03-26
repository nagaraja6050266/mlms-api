package com.example.mlms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDate issuedDate;

    @Column(nullable = false)
    private String filePath;

    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public Certificate() {
    }

    public Certificate(Integer certificateId, CertificateType certificateType, LocalDate issuedDate, String filePath) {
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

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
