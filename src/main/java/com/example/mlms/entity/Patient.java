package com.example.mlms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
@Getter
@Setter
public class Patient {
    @Id
    private String patientId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String contact;

    private String husbandName;
    private String fatherName;
    private String address;

    @Column(nullable = false)
    private LocalDate admissionDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Getters and setters...
}
