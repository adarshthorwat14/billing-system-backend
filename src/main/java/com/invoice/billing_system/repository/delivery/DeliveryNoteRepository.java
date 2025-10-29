package com.invoice.billing_system.repository.delivery;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.billing_system.model.delivery.DeliveryNote;

@Repository
public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, String> {
//    List<DeliveryNote> findByRequestId(String requestId);
    List<DeliveryNote> findByStatus(String status);
//    DeliveryNote findByRequestId(String requestId);
    List<DeliveryNote> findByRequestId(String requestId);
    List<DeliveryNote> findByPlantName(String plantName);
    List<DeliveryNote> findByDistributorId(String distributorId);

    Optional<DeliveryNote> findByDistributorIdAndDeliveryNoteId(String distributorId, String deliveryNoteId);

}

