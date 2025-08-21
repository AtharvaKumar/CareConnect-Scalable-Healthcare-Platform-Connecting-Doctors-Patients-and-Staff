package com.hospital.doctorservice.repository;

import com.hospital.doctorservice.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findBySpecializationContainingIgnoreCaseAndDoctorNameContainingIgnoreCase(String specialization, String doctorName, Pageable pageable);
}
