package com.hospital.billingservice.controller;

import com.hospital.billingservice.entity.Bill;
import com.hospital.billingservice.service.BillingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/billing")
public class BillingController {
    private final BillingService service;
    public BillingController(BillingService service){ this.service = service; }

    @PostMapping public ResponseEntity<Bill> add(@Valid @RequestBody Bill b){ return ResponseEntity.ok(service.create(b)); }
    @GetMapping public ResponseEntity<List<Bill>> all(){ return ResponseEntity.ok(service.all()); }
    @GetMapping("/{id}") public ResponseEntity<Bill> byId(@PathVariable Long id){ return ResponseEntity.ok(service.byId(id)); }
    @PostMapping("/{id}/pay") public ResponseEntity<Bill> pay(@PathVariable Long id){ return ResponseEntity.ok(service.markPaid(id)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
