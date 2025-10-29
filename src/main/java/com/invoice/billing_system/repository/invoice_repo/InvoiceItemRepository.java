package com.invoice.billing_system.repository.invoice_repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.billing_system.model.invoice.Invoice;
import com.invoice.billing_system.model.invoice.InvoiceItem;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

	void deleteAllByInvoice(Invoice invoice);
}

