package com.invoice.billing_system.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.billing_system.model.client.Client;
import com.invoice.billing_system.model.distributor.Distributor;
import com.invoice.billing_system.model.product_mng.ProductManagement;
import com.invoice.billing_system.model.user_dto.UserDetailsResponse;
import com.invoice.billing_system.model.user_dto.UserStatusUpdateRequest;
import com.invoice.billing_system.repository.client_repo.ClientRepository;
import com.invoice.billing_system.repository.distributor_repo.DistributorRepository;
import com.invoice.billing_system.repository.productMng_repo.ProductMngRepository;

@RestController
@RequestMapping("/api/users")
public class UserManagementController {

    @Autowired private ClientRepository clientRepository;
    @Autowired private DistributorRepository distributorRepository;
    @Autowired private ProductMngRepository productMngRepository;

    @PutMapping("/update-status")
    public ResponseEntity<String> updateUserStatus(@RequestBody UserStatusUpdateRequest request) {
    	 String id = request.getId();
    	    String type = request.getUserType().toLowerCase();
    	    boolean newStatus = request.isStatus();
    	    String newPassword = request.getPassword();

    	    switch (type) {
    	        case "client":
    	            Client client = clientRepository.findById(id)
    	                .orElseThrow(() -> new RuntimeException("Client not found"));
    	            client.setStatus(newStatus);
    	            if (newPassword != null && !newPassword.trim().isEmpty()) {
    	                client.setPassword(newPassword);
    	            }
    	            clientRepository.save(client);
    	            break;

    	        case "distributor":
    	            Distributor distributor = distributorRepository.findById(id)
    	                .orElseThrow(() -> new RuntimeException("Distributor not found"));
    	            distributor.setActive(newStatus);
    	            if (newPassword != null && !newPassword.trim().isEmpty()) {
    	                distributor.setPassword(newPassword);
    	            }
    	            distributorRepository.save(distributor);
    	            break;

    	        case "productmanager":
    	            ProductManagement manager = productMngRepository.findById(id)
    	                .orElseThrow(() -> new RuntimeException("Product Manager not found"));
    	            manager.setStatus(newStatus);
    	            if (newPassword != null && !newPassword.trim().isEmpty()) {
    	                manager.setPassword(newPassword);
    	            }
    	            productMngRepository.save(manager);
    	            break;

    	        default:
    	            return ResponseEntity.badRequest().body("Invalid user type");
    	    }

    	    return ResponseEntity.ok("User status and password updated successfully.");
    }
    
    
    @GetMapping("/get-user")
    public ResponseEntity<UserDetailsResponse> getUserById(
        @RequestParam String id,
        @RequestParam String type
    ) {
        UserDetailsResponse dto = new UserDetailsResponse();
        
        switch (type.toLowerCase()) {
            case "client":
                Client client = clientRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Client not found"));
                dto.setId(client.getId());
                dto.setName(client.getName());
                dto.setEmail(client.getEmail());
                dto.setPhone(client.getPhone());
                dto.setPassword(client.getPassword());
                dto.setStatus(client.isStatus());
                break;

            case "distributor":
                Distributor dist = distributorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Distributor not found"));
                dto.setId(dist.getId());
                dto.setName(dist.getName());
                dto.setEmail(dist.getEmail());
                dto.setPhone(dist.getPhone());
                dto.setPassword(dist.getPassword());
                dto.setStatus(dist.isActive());
                break;

            case "productmanager":
                ProductManagement pm = productMngRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product Manager not found"));
                dto.setId(pm.getId());
                dto.setName(pm.getName());
                dto.setEmail(pm.getEmail());
                dto.setPhone(pm.getPhoneNumber());
                dto.setPassword(pm.getPassword());
                dto.setStatus(pm.isStatus());
                break;

            default:
                throw new RuntimeException("Invalid user type");
        }

        return ResponseEntity.ok(dto);
    }


}

