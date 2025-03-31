package com.example.mlms.repository;

import com.example.mlms.entity.BirthCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthCertificateRepository extends JpaRepository<BirthCertificate, Integer> {
}
