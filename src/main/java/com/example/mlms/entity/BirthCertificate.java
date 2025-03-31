package com.example.mlms.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "birth_certificates")
public class BirthCertificate {
    @Id
    @Column(name = "certificate_id", nullable = false)
    private Integer certificateId;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "certificate_id", referencedColumnName = "certificate_id", insertable = false, updatable = false)
    private Certificate certificate;

    @Column(name = "birth_weight")
    private Double birthWeight;

    @Column(name = "time_of_birth", nullable = false)
    private String timeOfBirth;

    @Column(name = "father_name", nullable = false)
    private String fatherName;

    @Column(name = "mother_name", nullable = false)
    private String motherName;

    public BirthCertificate() {
    }

    public BirthCertificate(Integer certificateId, Double birthWeight, String timeOfBirth, String fatherName, String motherName) {
        this.certificateId = certificateId;
        this.birthWeight = birthWeight;
        this.timeOfBirth = timeOfBirth;
        this.fatherName = fatherName;
        this.motherName = motherName;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Double getBirthWeight() {
        return birthWeight;
    }

    public void setBirthWeight(Double birthWeight) {
        this.birthWeight = birthWeight;
    }

    public String getTimeOfBirth() {
        return timeOfBirth;
    }

    public void setTimeOfBirth(String timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
}
