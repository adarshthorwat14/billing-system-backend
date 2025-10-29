package com.invoice.billing_system.repository.hsn;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.billing_system.model.hsn.Hsn;

public interface HsnRepository extends JpaRepository<Hsn, String> {
}
