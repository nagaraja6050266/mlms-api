package com.example.mlms.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lab_reports")
public class LabReport {
    @Id
    @Column(name = "certificate_id", nullable = false)
    private Integer certificateId;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "certificate_id", referencedColumnName = "certificate_id", insertable = false, updatable = false)
    private Certificate certificate;

    @Column(name = "test_type", nullable = false)
    private String testType;

    @Column(name = "test_results", nullable = false)
    private String testResults;

    public LabReport() {
    }

    public LabReport(Integer certificateId, String testType, String testResults) {
        this.certificateId = certificateId;
        this.testType = testType;
        this.testResults = testResults;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }
}
