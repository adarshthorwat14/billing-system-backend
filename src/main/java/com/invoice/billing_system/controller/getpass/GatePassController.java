package com.invoice.billing_system.controller.getpass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.billing_system.model.getpass.GatePass;
import com.invoice.billing_system.repository.getpass.GatePassRepository;
import com.invoice.billing_system.service.gatepass.GatePassService;

@RestController
@RequestMapping("/api/gate-passes")
public class GatePassController {

    @Autowired
    private GatePassRepository gatePassRepository;
    
    @Autowired
    private GatePassService gatePassService;
    
    
    @PutMapping("/update")
    public ResponseEntity<GatePass> updateGatePass(@RequestParam String gatePassId, @RequestBody GatePass updatedData) {
        GatePass updated = gatePassService.updateGatePass(gatePassId, updatedData);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteGatePass(@RequestParam String gatePassId) {
        gatePassService.deleteGatePass(gatePassId);
        return ResponseEntity.ok("Gate Pass deleted successfully.");
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<GatePass>> getAllGatePasses() {
        List<GatePass> gatePasses = gatePassService.getAllGatePasses();
        return ResponseEntity.ok(gatePasses);
    }
}

