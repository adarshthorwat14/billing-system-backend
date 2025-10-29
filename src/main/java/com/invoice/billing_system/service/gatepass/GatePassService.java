package com.invoice.billing_system.service.gatepass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.invoice.billing_system.model.getpass.GatePass;
import com.invoice.billing_system.repository.getpass.GatePassRepository;


public interface GatePassService {
	
	GatePass updateGatePass(String gatePassId, GatePass updatedData);
    void deleteGatePass(String gatePassId);
    List<GatePass> getAllGatePasses();
}
