package com.example.mlms.controller;

import com.example.mlms.entity.Certificate;
import com.example.mlms.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {
    @Autowired
    private CertificateService certificateService;

    @GetMapping
    public List<Certificate> getAllCertificates() {
        return certificateService.getAllCertificates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable Integer id) {
        return certificateService.getCertificateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/types")
    public ResponseEntity<?> getCertificateTypes(){
        return ResponseEntity.ok(certificateService.getCertificateTypes());
    }

    @PostMapping
    public ResponseEntity<?> createCertificate(@RequestBody Map<String, Object> request) {
        try {
            Certificate certificate = certificateService.createCertificate(request);
            return ResponseEntity.ok(certificate);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificate> updateCertificate(@PathVariable Integer id, @RequestBody Certificate certificateDetails) {
        try {
            return ResponseEntity.ok(certificateService.updateCertificate(id, certificateDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Integer id) {
        certificateService.deleteCertificate(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/initialize")
    public ResponseEntity<?> initializeCertificateTypes() {
        return ResponseEntity.ok(certificateService.initializeCertificateTypes());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Certificate>> getCertificatesByPatientId(@PathVariable Integer patientId) {
        return ResponseEntity.ok(certificateService.getCertificatesByPatientId(patientId));
    }

    @GetMapping("/type")
    public ResponseEntity<List<Certificate>> getCertificatesByType(@RequestParam("type") Integer type) {
        return ResponseEntity.ok(certificateService.getCertificatesByType(type));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getCertificateCountByType() {
        return ResponseEntity.ok(certificateService.getCertificateCountByType());
    }

    @PostMapping("/search")
    public ResponseEntity<List<Certificate>> searchCertificates(@RequestParam String keyword) {
        return ResponseEntity.ok(certificateService.searchCertificates(keyword));
    }
}
