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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String patientId;
    private String name;
    private String dob;
    private String gender;
    private String contact;
    private String husbandName;
    private String fatherName;
    private String address;
    private String admissionDate;

    // Getters and setters...
}