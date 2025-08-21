package com.hospital.doctorservice.controller;

import com.hospital.doctorservice.dto.DoctorUpdateRequest;
import com.hospital.doctorservice.entity.Doctor;
import com.hospital.doctorservice.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService service;
    public DoctorController(DoctorService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Doctor> add(@Valid @RequestBody Doctor doctor) { return ResponseEntity.ok(service.create(doctor)); }

    @GetMapping
    public ResponseEntity<List<Doctor>> all() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> byId(@PathVariable Long id) { return ResponseEntity.ok(service.findById(id)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { service.deleteById(id); return ResponseEntity.noContent().build(); }

    @PatchMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable Long id, @Valid @RequestBody DoctorUpdateRequest req) { return ResponseEntity.ok(service.updatePartial(id, req)); }

    @GetMapping("/search")
    public ResponseEntity<Page<Doctor>> search(@RequestParam(required=false) String specialization,
                                               @RequestParam(required=false) String name,
                                               @RequestParam(defaultValue="0") int page,
                                               @RequestParam(defaultValue="10") int size,
                                               @RequestParam(required=false) String sort) {
        return ResponseEntity.ok(service.search(specialization, name, page, size, sort));
    }
}
