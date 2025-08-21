package com.hospital.billingservice.repository;

import com.hospital.billingservice.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BillRepository extends JpaRepository<Bill, Long> { }
