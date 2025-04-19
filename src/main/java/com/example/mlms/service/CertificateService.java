package com.example.mlms.service;

import jakarta.transaction.Transactional;

import com.example.mlms.entity.Certificate;
import com.example.mlms.entity.CertificateType;
import com.example.mlms.repository.BirthCertificateRepository;
import com.example.mlms.repository.CertificateRepository;
import com.example.mlms.repository.CertificateTypeRepository;
import com.example.mlms.entity.BirthCertificate;
import com.example.mlms.entity.VaccinationCertificate;
import com.example.mlms.entity.LabReport;
import com.example.mlms.repository.LabReportRepository;
import com.example.mlms.repository.PatientRepository;
import com.example.mlms.repository.VaccinationCertificateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VaccinationCertificateRepository vaccinationCertificateRepository;

    @Autowired
    private BirthCertificateRepository birthCertificateRepository;

    @Autowired
    private LabReportRepository labReportRepository;

    @Autowired
    private CertificateTypeRepository certificateTypeRepository;

    public List<Certificate> getAllCertificates() {
        List<Certificate> certificates = certificateRepository.findAll();
        for(Certificate certificate:certificates){
            certificate.setCertificateDetails(getCertificateDetails(certificate.getCertificateId()));
        }
        return certificates;
    }

    public Optional<Certificate> getCertificateById(Integer id) {
        return certificateRepository.findById(id).map(certificate -> {
            certificate.setCertificateDetails(getCertificateDetails(certificate.getCertificateId()));
            return certificate;
        });
    }

    @Transactional
    public Certificate createCertificate(Map<String, Object> request) {
        ObjectMapper mapper = new ObjectMapper();

        // Extract common certificate fields
        Certificate certificate = new Certificate();
        certificate.setIssuedDate(request.get("issuedDate").toString()); // Changed to String
        certificate.setFilePath(request.get("filePath").toString());
        certificate.setExpiryDate(request.get("expiryDate").toString()); // Changed to String
        certificate.setCertificateType(certificateTypeRepository.findById(
            Integer.parseInt(((Map<String, Object>) request.get("certificateType")).get("certificateTypeId").toString())
        ).orElseThrow(() -> new RuntimeException("Invalid certificate type")));
        certificate.setPatient(patientRepository.findById(
            Integer.parseInt(((Map<String, Object>) request.get("patient")).get("patientId").toString())
        ).orElseThrow(() -> new RuntimeException("Invalid patient ID")));

        // Save the certificate to generate the ID
        certificate = certificateRepository.save(certificate);

        // Handle specific certificate details based on type
        Integer typeId = certificate.getCertificateType().getCertificateTypeId();
        Map<String, Object> certificateDetails = (Map<String, Object>) request.get("certificateDetails");

        Object certificateDetailsObj;
        if (typeId == 1) { // Birth Certificate
            BirthCertificate birthCertificate = mapper.convertValue(certificateDetails, BirthCertificate.class);
            birthCertificate.setCertificateId(certificate.getCertificateId());
            certificateDetailsObj = birthCertificateRepository.save(birthCertificate);
        } else if (typeId == 2) { // Vaccination Certificate
            VaccinationCertificate vaccinationCertificate = mapper.convertValue(certificateDetails, VaccinationCertificate.class);
            vaccinationCertificate.setCertificateId(certificate.getCertificateId());
            certificateDetailsObj= vaccinationCertificateRepository.save(vaccinationCertificate);
        } else if (typeId == 3) { // Lab Reports
            LabReport labReport = mapper.convertValue(certificateDetails, LabReport.class);
            labReport.setCertificateId(certificate.getCertificateId());
            certificateDetailsObj = labReportRepository.save(labReport);
        } else {
            throw new RuntimeException("Unsupported certificate type");
        }
        certificate.setCertificateDetails(certificateDetailsObj);
        return certificate;
    }

    public Certificate updateCertificate(Integer id, Certificate certificateDetails) {
        return certificateRepository.findById(id).map(certificate -> {
            certificate.setIssuedDate(certificateDetails.getIssuedDate()); // Changed to String
            certificate.setFilePath(certificateDetails.getFilePath());
            certificate.setCertificateType(certificateDetails.getCertificateType());
            Certificate cert = certificateRepository.save(certificate);
            cert.setCertificateDetails(getCertificateDetails(cert.getCertificateId()));
            return cert;
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

    public List<Certificate> getCertificatesByType(Integer certificateType) {
        List<Certificate> certificates = certificateRepository.findAll();
        List<Certificate> filteredCertificates = new ArrayList<>();
        for (Certificate certificate : certificates) {
            if (Objects.equals(certificate.getCertificateType().getCertificateTypeId(), certificateType)) {
                filteredCertificates.add(certificate);
            }
        }
        return filteredCertificates;
    }

    public List<Certificate> getCertificatesIssuedAfter(String date) { // Changed parameter to String
        List<Certificate> certificates = certificateRepository.findAll();
        List<Certificate> filteredCertificates = new ArrayList<>();
        for (Certificate certificate : certificates) {
            if (LocalDate.parse(certificate.getIssuedDate()).isAfter(LocalDate.parse(date))) { // Parse String to LocalDate for comparison
                filteredCertificates.add(certificate);
            }
        }
        return filteredCertificates;
    }

    public boolean isCertificateValid(Integer id) {
        Optional<Certificate> certificate = certificateRepository.findById(id);
        if (certificate.isPresent()) {
            return LocalDate.parse(certificate.get().getExpiryDate()).isAfter(LocalDate.now()); // Parse String to LocalDate for comparison
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
                certificate.setCertificateDetails(getCertificateDetails(certificate.getCertificateId()));
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
                certificate.setCertificateDetails(getCertificateDetails(certificate.getCertificateId()));
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
            new CertificateType(2, "Vaccination Certificate", "Issued for vaccination records"),
            new CertificateType(3, "Lab Reports", "Issued for Lab Reports"),
            new CertificateType(4, "Death Certificate", "Issued for death records"),
            new CertificateType(5, "Medical Fitness Certificate", "Issued for medical fitness verification"),
            new CertificateType(6, "Disability Certificate", "Issued for disability verification"),
            new CertificateType(7, "Blood Donation Certificate", "Issued for blood donation records"),
            new CertificateType(8, "Organ Donation Certificate", "Issued for organ donation records")
        );

        List<CertificateType> savedTypes = new ArrayList<>();

        for (CertificateType type : defaultTypes) {
            savedTypes.add(certificateTypeRepository.save(type));
        }
        return savedTypes;
    }

    public Object getCertificateDetails(Integer id) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> new RuntimeException("Certificate not found"));
        Integer typeId = certificate.getCertificateType().getCertificateTypeId();
        switch(typeId) {
            case 1:
                return birthCertificateRepository.findById(id).orElseThrow(() -> new RuntimeException("Certificate details not found"));
            case 2:
                return vaccinationCertificateRepository.findById(id).orElseThrow(() -> new RuntimeException("Certificate details not found"));
            case 3:
                return labReportRepository.findById(id).orElseThrow(() -> new RuntimeException("Certificate details not found"));
            default:
                throw new RuntimeException("Unsupported certificate type");
        }
    }
}
