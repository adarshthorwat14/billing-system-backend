package com.invoice.billing_system.service.vehicle;

import java.util.List;

import com.invoice.billing_system.model.dispatch_dto.ApprovedVehicleRequestDTO;
import com.invoice.billing_system.model.vehicle.VehicleRequestPlant;

public interface VehicleService {
	
    VehicleRequestPlant requestVehicle(VehicleRequestPlant request);
    List<VehicleRequestPlant> getAllRequests();
    void approveRequest(String transportId);
    List<VehicleRequestPlant> getAllPendingRequests();
   
//     List<VehicleRequestPlant> getApprovedRequests();
     List<ApprovedVehicleRequestDTO> getApprovedRequests();
     List<VehicleRequestPlant> getAllApprovedRequests();


}
