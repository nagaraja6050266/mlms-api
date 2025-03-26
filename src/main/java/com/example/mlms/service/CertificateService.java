package com.example.mlms.service;

import com.example.mlms.entity.Certificate;
import com.example.mlms.entity.CertificateType;
import com.example.mlms.repository.CertificateRepository;
import com.example.mlms.repository.CertificateTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private CertificateTypeRepository certificateTypeRepository;

    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    public Optional<Certificate> getCertificateById(Integer id) {
        return certificateRepository.findById(id);
    }

    public Certificate createCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    public Certificate updateCertificate(Integer id, Certificate certificateDetails) {
        return certificateRepository.findById(id).map(certificate -> {
            certificate.setIssuedDate(certificateDetails.getIssuedDate());
            certificate.setFilePath(certificateDetails.getFilePath());
            certificate.setCertificateType(certificateDetails.getCertificateType());
            return certificateRepository.save(certificate);
        }).orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    public void deleteCertificate(Integer id) {
        certificateRepository.deleteById(id);
    }

    public HashMap<Integer, Certificate> getCertificatesByIdMap() {
        List<Certificate> certificates = certificateRepository.findAll();
        HashMap<Integer, Certificate> certificateMap = new HashMap<>();
        for (Certificate certificate : certificates) {
            certificateMap.put(certificate.getCertificateId(), certificate);
        }
        return certificateMap;
    }

    public HashMap<Integer, List<Certificate>> getCertificatesByPatientIdMap() {
        List<Certificate> certificates = certificateRepository.findAll();
        HashMap<Integer, List<Certificate>> patientCertificateMap = new HashMap<>();
        for (Certificate certificate : certificates) {
            Integer patientId = certificate.getPatient().getPatientId();
            if (!patientCertificateMap.containsKey(patientId)) {
                patientCertificateMap.put(patientId, new ArrayList<>());
            }
            patientCertificateMap.get(patientId).add(certificate);
        }
        return patientCertificateMap;
    }

    public List<Certificate> getCertificatesByType(String certificateType) {
        List<Certificate> certificates = certificateRepository.findAll();
        List<Certificate> filteredCertificates = new ArrayList<>();
        for (Certificate certificate : certificates) {
            if (certificate.getCertificateType().getTypeName().equalsIgnoreCase(certificateType)) {
                filteredCertificates.add(certificate);
            }
        }
        return filteredCertificates;
    }

    public List<Certificate> getCertificatesIssuedAfter(LocalDate date) {
        List<Certificate> certificates = certificateRepository.findAll();
        List<Certificate> filteredCertificates = new ArrayList<>();
        for (Certificate certificate : certificates) {
            if (certificate.getIssuedDate().isAfter(date)) {
                filteredCertificates.add(certificate);
            }
        }
        return filteredCertificates;
    }

    public boolean isCertificateValid(Integer id) {
        Optional<Certificate> certificate = certificateRepository.findById(id);
        if (certificate.isPresent()) {
            return certificate.get().getExpiryDate().isAfter(LocalDate.now());
        }
        return false;
    }

    public Map<String, Long> getCertificateCountByType() {
        List<Certificate> certificates = certificateRepository.findAll();
        HashMap<String, Long> countMap = new HashMap<>();
        for (Certificate certificate : certificates) {
            String type = certificate.getCertificateType().getTypeName();
            countMap.put(type, countMap.getOrDefault(type, 0L) + 1);
        }
        return countMap;
    }

    public List<Certificate> getCertificatesByPatientId(Integer patientId) {
        List<Certificate> certificates = certificateRepository.findAll();
        List<Certificate> filteredCertificates = new ArrayList<>();
        for (Certificate certificate : certificates) {
            if (certificate.getPatient().getPatientId().equals(patientId)) {
                filteredCertificates.add(certificate);
            }
        }
        return filteredCertificates;
    }

    public void bulkDeleteCertificates(List<Integer> ids) {
        for (Integer id : ids) {
            certificateRepository.deleteById(id);
        }
    }

    public List<Certificate> searchCertificates(String keyword) {
        List<Certificate> certificates = certificateRepository.findAll();
        List<Certificate> filteredCertificates = new ArrayList<>();
        for (Certificate certificate : certificates) {
            if (certificate.getCertificateType().getTypeName().toLowerCase().contains(keyword.toLowerCase()) || certificate.getFilePath().toLowerCase().contains(keyword.toLowerCase())) {
                filteredCertificates.add(certificate);
            }
        }
        return filteredCertificates;
    }

    public List<CertificateType> getCertificateTypes(){
        return certificateTypeRepository.findAll();
    }

    public List<CertificateType> initializeCertificateTypes() {
        certificateRepository.deleteAll();
        List<CertificateType> defaultTypes = List.of(
            new CertificateType(1, "Birth Certificate", "Issued for birth registration"),
            new CertificateType(2, "Death Certificate", "Issued for death registration"),
            new CertificateType(3, "Atopsy Report", "Issued for atopsy reports"),
            new CertificateType(4, "Vaccination Certificate", "Issued for vaccination records"),
            new CertificateType(5, "Medical Fitness Certificate", "Issued for medical fitness verification"),
            new CertificateType(6, "Disability Certificate", "Issued for disability verification"),
            new CertificateType(7, "Blood Donation Certificate", "Issued for blood donation records"),
            new CertificateType(8, "Organ Donation Certificate", "Issued for organ donation records")
        );

        List<CertificateType> savedTypes = new ArrayList<>();

        for(CertificateType type:defaultTypes){
            savedTypes.add(certificateTypeRepository.save(type));
        }
        return savedTypes;
    }
}
