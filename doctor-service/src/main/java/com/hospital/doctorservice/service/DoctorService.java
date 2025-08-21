package com.hospital.doctorservice.service;

import com.hospital.doctorservice.dto.DoctorUpdateRequest;
import com.hospital.doctorservice.entity.Doctor;
import com.hospital.doctorservice.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository repo;
    public DoctorService(DoctorRepository repo) { this.repo = repo; }

    public Doctor create(Doctor d) { return repo.save(d); }
    public List<Doctor> findAll() { return repo.findAll(); }
    public Doctor findById(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found: " + id)); }
    public void deleteById(Long id) { repo.deleteById(id); }
    public Doctor updatePartial(Long id, DoctorUpdateRequest req) {
        Doctor d = findById(id);
        d.setSpecialization(req.getSpecialization());
        d.setExperienceYears(req.getExperienceYears());
        return repo.save(d);
    }
    public Page<Doctor> search(String specialization, String name, int page, int size, String sort) {
        Sort s = Sort.by((sort == null || sort.isBlank()) ? "doctorName" : sort).ascending();
        Pageable pageable = PageRequest.of(page, size, s);
        return repo.findBySpecializationContainingIgnoreCaseAndDoctorNameContainingIgnoreCase(
                specialization == null ? "" : specialization,
                name == null ? "" : name,
                pageable
        );
    }
}
