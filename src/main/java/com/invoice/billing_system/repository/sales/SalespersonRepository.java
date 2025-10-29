package com.invoice.billing_system.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.billing_system.model.sales.Salesperson;

public interface SalespersonRepository extends JpaRepository<Salesperson, Long> {
}
