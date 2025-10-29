package com.invoice.billing_system.service.gatepass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.billing_system.model.getpass.GatePass;
import com.invoice.billing_system.repository.getpass.GatePassRepository;

@Service
public class GatePassServiceImpl implements GatePassService {

    @Autowired
    private GatePassRepository gatePassRepository;

    @Override
    public GatePass updateGatePass(String gatePassId, GatePass updatedData) {
        GatePass gatePass = gatePassRepository.findById(gatePassId)
                .orElseThrow(() -> new RuntimeException("Gate Pass not found"));

        gatePass.setDriverName(updatedData.getDriverName());
        gatePass.setDriverPhoneNumber(updatedData.getDriverPhoneNumber());
        gatePass.setVehicleNumber(updatedData.getVehicleNumber());
        gatePass.setFromLocation(updatedData.getFromLocation());
        gatePass.setToLocation(updatedData.getToLocation());
        gatePass.setAuthorisedBy(updatedData.getAuthorisedBy());
        gatePass.setStatus(updatedData.getStatus());

        return gatePassRepository.save(gatePass);
    }

    @Override
    public void deleteGatePass(String gatePassId) {
        GatePass gatePass = gatePassRepository.findById(gatePassId)
            .orElseThrow(() -> new RuntimeException("Gate Pass not found with ID: " + gatePassId));
        gatePassRepository.delete(gatePass);
    }


    @Override
    public List<GatePass> getAllGatePasses() {
        return gatePassRepository.findAll();
    }
}

