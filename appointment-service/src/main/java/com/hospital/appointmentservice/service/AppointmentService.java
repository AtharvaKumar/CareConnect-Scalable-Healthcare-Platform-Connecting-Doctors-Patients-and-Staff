package com.hospital.appointmentservice.service;

import com.hospital.appointmentservice.entity.Appointment;
import com.hospital.appointmentservice.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository repo;
    public AppointmentService(AppointmentRepository repo){ this.repo = repo; }
    public Appointment create(Appointment a){ return repo.save(a); }
    public List<Appointment> all(){ return repo.findAll(); }
    public Appointment byId(Long id){ return repo.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found: " + id)); }
    public void delete(Long id){ repo.deleteById(id); }
}
