package com.invoice.billing_system.repository.getpass;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.billing_system.model.getpass.GatePass;

public interface GatePassRepository extends JpaRepository<GatePass, String> {

    // Optional: custom methods if needed later
    List<GatePass> findByRequestId(String requestId);
    List<GatePass> findByDeliveryNoteId(String deliveryNoteId);
}
