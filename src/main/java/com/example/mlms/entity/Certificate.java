package com.example.mlms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "certificates")
@Getter
@Setter
public class Certificate {
    @Id
    private String certificateId;

    @ManyToOne
    @JoinColumn(name = "certificate_type_id", referencedColumnName = "certificate_type_id") // Ensure correct column mapping
    private CertificateType certificateType;

    @Column(nullable = false)
    private LocalDate issuedDate;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Getters and setters...
}
