package com.example.mlms.service;

import com.example.mlms.entity.Certificate;
import com.example.mlms.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;

    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    public Optional<Certificate> getCertificateById(String id) {
        return certificateRepository.findById(id);
    }

    public Certificate createCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    public Certificate updateCertificate(String id, Certificate certificateDetails) {
        return certificateRepository.findById(id).map(certificate -> {
            certificate.setIssuedDate(certificateDetails.getIssuedDate());
            certificate.setFilePath(certificateDetails.getFilePath());
            certificate.setCertificateType(certificateDetails.getCertificateType());
            return certificateRepository.save(certificate);
        }).orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    public void deleteCertificate(String id) {
        certificateRepository.deleteById(id);
    }
}
