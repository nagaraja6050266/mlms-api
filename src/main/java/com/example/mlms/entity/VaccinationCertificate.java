package com.example.mlms.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vaccination_certificates")
public class VaccinationCertificate {
    @Id
    @Column(name = "certificate_id", nullable = false)
    private Integer certificateId;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "certificate_id", referencedColumnName = "certificate_id", insertable = false, updatable = false)
    private Certificate certificate;

    @Column(name = "vaccine_name", nullable = false)
    private String vaccineName;

    @Column(name = "dose_number", nullable = false)
    private Integer doseNumber;

    @Column(name = "next_due_date")
    private String nextDueDate;

    public VaccinationCertificate() {
    }

    public VaccinationCertificate(Integer certificateId, String vaccineName, Integer doseNumber, String nextDueDate) {
        this.certificateId = certificateId;
        this.vaccineName = vaccineName;
        this.doseNumber = doseNumber;
        this.nextDueDate = nextDueDate;
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

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public Integer getDoseNumber() {
        return doseNumber;
    }

    public void setDoseNumber(Integer doseNumber) {
        this.doseNumber = doseNumber;
    }

    public String getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(String nextDueDate) {
        this.nextDueDate = nextDueDate;
    }
}
