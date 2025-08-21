package com.hospital.patientservice.controller;

import com.hospital.patientservice.entity.Patient;
import com.hospital.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private final PatientService service;
    public PatientController(PatientService service){ this.service = service; }

    @PostMapping public ResponseEntity<Patient> add(@Valid @RequestBody Patient p){ return ResponseEntity.ok(service.create(p)); }
    @GetMapping public ResponseEntity<List<Patient>> all(){ return ResponseEntity.ok(service.all()); }
    @GetMapping("/{id}") public ResponseEntity<Patient> byId(@PathVariable Long id){ return ResponseEntity.ok(service.byId(id)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
