package com.example.mlms.repository;

import com.example.mlms.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
    List<Certificate> findByCertificateType_TypeName(String typeName);
}
