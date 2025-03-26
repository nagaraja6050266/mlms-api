package com.example.mlms.repository;

import com.example.mlms.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    // Additional query methods if needed
}
