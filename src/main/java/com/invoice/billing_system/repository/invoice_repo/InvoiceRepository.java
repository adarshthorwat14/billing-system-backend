package com.invoice.billing_system.repository.invoice_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.billing_system.model.invoice.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
	
	List<Invoice> findByClientId(String clientId);
	List<Invoice> findByDistributor_Id(String distributorId);

}			

