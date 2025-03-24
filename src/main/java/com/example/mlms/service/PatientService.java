package com.example.mlms.service;

import com.example.mlms.entity.Patient;
import com.example.mlms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(String id, Patient patientDetails) {
        return patientRepository.findById(id).map(patient -> {
            patient.setName(patientDetails.getName());
            patient.setDob(patientDetails.getDob());
            patient.setGender(patientDetails.getGender());
            patient.setContact(patientDetails.getContact());
            patient.setHusbandName(patientDetails.getHusbandName());
            patient.setFatherName(patientDetails.getFatherName());
            patient.setAddress(patientDetails.getAddress());
            patient.setAdmissionDate(patientDetails.getAdmissionDate());
            return patientRepository.save(patient);
        }).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }
}
