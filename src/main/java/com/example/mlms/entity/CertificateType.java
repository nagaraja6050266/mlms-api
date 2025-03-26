package com.example.mlms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "certificate_types")
public class CertificateType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "certificate_type_id")
    private Integer certificateTypeId;

    @Column(nullable = false, unique = true)
    private String typeName;

    private String description;

    public CertificateType() {
    }

    public CertificateType(Integer certificateTypeId, String typeName, String description) {
        this.certificateTypeId = certificateTypeId;
        this.typeName = typeName;
        this.description = description;
    }

    public Integer getCertificateTypeId() {
        return certificateTypeId;
    }

    public void setCertificateTypeId(Integer certificateTypeId) {
        this.certificateTypeId = certificateTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
