package com.hospital.billingservice.service;

import com.hospital.billingservice.entity.Bill;
import com.hospital.billingservice.repository.BillRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BillingService {
    private final BillRepository repo;
    public BillingService(BillRepository repo){ this.repo = repo; }
    public Bill create(Bill b){ return repo.save(b); }
    public List<Bill> all(){ return repo.findAll(); }
    public Bill byId(Long id){ return repo.findById(id).orElseThrow(() -> new RuntimeException("Bill not found: " + id)); }
    public Bill markPaid(Long id){ Bill b = byId(id); b.setStatus("PAID"); return repo.save(b); }
    public void delete(Long id){ repo.deleteById(id); }
}
