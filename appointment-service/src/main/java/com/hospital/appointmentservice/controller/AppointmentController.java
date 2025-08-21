package com.hospital.appointmentservice.controller;

import com.hospital.appointmentservice.entity.Appointment;
import com.hospital.appointmentservice.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
    private final AppointmentService service;
    public AppointmentController(AppointmentService service){ this.service = service; }

    @PostMapping public ResponseEntity<Appointment> add(@Valid @RequestBody Appointment a){ return ResponseEntity.ok(service.create(a)); }
    @GetMapping public ResponseEntity<List<Appointment>> all(){ return ResponseEntity.ok(service.all()); }
    @GetMapping("/{id}") public ResponseEntity<Appointment> byId(@PathVariable Long id){ return ResponseEntity.ok(service.byId(id)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
