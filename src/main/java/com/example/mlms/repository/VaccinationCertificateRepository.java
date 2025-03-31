package com.example.mlms.repository;

import com.example.mlms.entity.VaccinationCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationCertificateRepository extends JpaRepository<VaccinationCertificate, Integer> {
}
