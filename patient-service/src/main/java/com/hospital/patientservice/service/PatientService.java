package com.hospital.patientservice.service;

import com.hospital.patientservice.entity.Patient;
import com.hospital.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository repo;
    public PatientService(PatientRepository repo){ this.repo = repo; }
    public Patient create(Patient p){ return repo.save(p); }
    public List<Patient> all(){ return repo.findAll(); }
    public Patient byId(Long id){ return repo.findById(id).orElseThrow(() -> new RuntimeException("Patient not found: " + id)); }
    public void delete(Long id){ repo.deleteById(id); }
}
