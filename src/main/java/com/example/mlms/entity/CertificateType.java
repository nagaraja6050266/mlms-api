package com.example.mlms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "certificate_types")
@Getter
@Setter
public class CertificateType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer certificateTypeId;

    @Column(nullable = false, unique = true)
    private String typeName;

    private String description;

    // Getters and setters...
}
