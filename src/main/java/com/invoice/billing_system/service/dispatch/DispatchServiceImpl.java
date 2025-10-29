package com.invoice.billing_system.service.dispatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.billing_system.model.delivery.DeliveryNote;
import com.invoice.billing_system.model.dispatch.Dispatch;
import com.invoice.billing_system.model.distributor.ProductRequest;
import com.invoice.billing_system.model.getpass.GatePass;
import com.invoice.billing_system.repository.delivery.DeliveryNoteRepository;
import com.invoice.billing_system.repository.dispatch.DispatchRepository;
import com.invoice.billing_system.repository.distributor_repo.ProductRequestRepository;
import com.invoice.billing_system.repository.getpass.GatePassRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    private DispatchRepository dispatchRepository;
    
    @Autowired
    private ProductRequestRepository productRequestRepository;

    @Autowired
   private  DeliveryNoteRepository deliveryNoteRepository;
    
    @Autowired
	private GatePassRepository gatePassRepository;
    
    private static final AtomicInteger dispatchCounter = new AtomicInteger(1);
    
    @Override
    @Transactional
    public Dispatch createDispatch(Dispatch dispatch) {
        // Count existing dispatches
        long count = dispatchRepository.count() + 1;

        // Generate Dispatch ID in format: DP01/001/REQID
        String dispatchId = "DP01/" + String.format("%03d", count) + "/" + dispatch.getRequestId();
        dispatch.setDispatchId(dispatchId);
        dispatch.setStatus("DISPATCHED");
        dispatch.setDispatchDate(LocalDate.now());

        // Save dispatch
        Dispatch savedDispatch = dispatchRepository.save(dispatch);
        long getpassCount = gatePassRepository.count() +1;
        GatePass gatePass = new GatePass();
        gatePass.setGatePassId("GP/"+String.format("%05d", getpassCount) + dispatch.getRequestId());
        gatePass.setRequestId(dispatch.getRequestId());
        gatePass.setDeliveryNoteId(dispatch.getDeliveryNoteId());
        gatePass.setVehicleNumber(dispatch.getVehicleNumber());
        gatePass.setDriverName(dispatch.getDriverName());
        gatePass.setFromLocation(dispatch.getFromLocation());
        gatePass.setToLocation(dispatch.getToLocation());
        gatePass.setAuthorisedBy(dispatch.getAuthorisedBy());
        gatePass.setStatus("ORDER RELEASED");
        gatePass.setGatePassDate(LocalDate.now());

        gatePassRepository.save(gatePass);
        
        // Update DeliveryNote status
        DeliveryNote note = deliveryNoteRepository.findById(dispatch.getDeliveryNoteId()).orElse(null);
        if (note != null) {
            note.setStatus("DISPATCHED");
            deliveryNoteRepository.save(note);
        }

        // Update ProductRequest status
        ProductRequest request = productRequestRepository.findById(dispatch.getRequestId()).orElse(null);
        if (request != null) {
            request.setStatus("DISPATCHED");
            productRequestRepository.save(request);
        }
        
       
        
        return savedDispatch;
    }


    @Override
    public List<Dispatch> getAllDispatches() {
        return dispatchRepository.findAll();
    }
}
