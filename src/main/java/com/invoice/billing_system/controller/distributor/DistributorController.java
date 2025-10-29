package com.invoice.billing_system.controller.distributor;

import com.invoice.billing_system.model.admin.Admin;
import com.invoice.billing_system.model.admin_dto.LoginRequest;
import com.invoice.billing_system.model.client.Client;
import com.invoice.billing_system.model.delivery_dto.ProductRequestWithDeliveryNoteDTO;
import com.invoice.billing_system.model.distributor.Distributor;
import com.invoice.billing_system.model.distributor.ProductRequest;
import com.invoice.billing_system.model.distributor.distributor_dto.ApprovalRequestDTO;
import com.invoice.billing_system.model.distributor.distributor_dto.DistributorStockDTO;
import com.invoice.billing_system.model.distributor.distributor_dto.ProductRequestDTO;
import com.invoice.billing_system.repository.client_repo.ClientRepository;
import com.invoice.billing_system.repository.distributor_repo.DistributorRepository;
import com.invoice.billing_system.repository.distributor_repo.ProductRequestRepository;
import com.invoice.billing_system.repository.productMng_repo.ProductMngRepository;
import com.invoice.billing_system.service.client_serv.ClientService;
import com.invoice.billing_system.service.distributor_serv.DistributorService;
import com.invoice.billing_system.service.distributor_serv.ProductRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/distributors")
public class DistributorController {

    @Autowired
    private DistributorService distributorService;

    private final DistributorRepository distributorRepository;
    
    private final ProductRequestRepository productRequestRepository;

    @Autowired
    public DistributorController(DistributorRepository distributorRepository,ProductRequestRepository productRequestRepository) {
        this.distributorRepository = distributorRepository;
        this.productRequestRepository = productRequestRepository;
    }
    

    @Autowired
    private ProductRequestService productRequestService;

    // POST: Create new product request
    @PostMapping("/request")
    public ResponseEntity<?> createRequest(@RequestBody ProductRequestDTO dto) {
        try {
            ProductRequest savedRequest = productRequestService.createRequest(dto);
            return ResponseEntity.ok(savedRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create product request: " + e.getMessage());
        }
    }
    
    @GetMapping("/all")
    public List<ProductRequestDTO> getAllRequests() {
        return productRequestService.getAllProductRequests();
    }
    
    @GetMapping("/{distributorId}/requests")
    public ResponseEntity<List<ProductRequestDTO>> getRequestsByDistributor(
            @PathVariable String distributorId) {
        
        List<ProductRequestDTO> requests = productRequestService.getRequestsByDistributor(distributorId);
        return ResponseEntity.ok(requests);
    }

    
    @PutMapping("/request/{requestId}/status")
    public ResponseEntity<ProductRequestWithDeliveryNoteDTO> updateRequestStatus(
            @PathVariable String requestId,
            @RequestBody ApprovalRequestDTO approvalRequest
    ) {
        ProductRequestWithDeliveryNoteDTO result = productRequestService.updateRequestStatus(
                requestId,
                approvalRequest.getStatus(),
                approvalRequest.getRemark(),
                approvalRequest.getPlantApprovals()
        );

        return ResponseEntity.ok(result);
    }


    @GetMapping("/count/approved")
    public ResponseEntity<Long> getApprovedRequestCount() {
        long count = productRequestRepository.countByStatus("APPROVED");
        return ResponseEntity.ok(count);
    }
    
    private ClientService clientService;

    @GetMapping("/{distributorId}/clients")
    public ResponseEntity<List<Client>> getClientsForDistributor(@PathVariable String distributorId) {
        List<Client> clients = clientService.getClientsByDistributorId(distributorId);
        return ResponseEntity.ok(clients);
    }
    
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getDistributorCount() {
        long count = distributorRepository.count();
        return ResponseEntity.ok(Collections.singletonMap("count", count));
    }
    
    @PostMapping
	public ResponseEntity<Distributor> createDistributor(@RequestBody Distributor distributor) {
    	Distributor saveDistributor = distributorService.saveDistributor(distributor);
		return new ResponseEntity<>(saveDistributor, HttpStatus.CREATED);
	}

    // GET: List All Distributors
//    @GetMapping
//    public ResponseEntity<List<Distributor>> getAllDistributors() {
//        List<Distributor> distributors = distributorRepository.findAll();
//        return ResponseEntity.ok(distributors);
//    }
    
    @GetMapping
	public ResponseEntity<List<Distributor>> getAllDistributors() {
		List<Distributor> distributors = distributorService.getAllDistributors();
		return new ResponseEntity<>(distributors, HttpStatus.OK);
	}
    
	 
	@GetMapping("/{id}")
	public ResponseEntity<Distributor> getDistributorById(@PathVariable String id) {
		Optional<Distributor> distributor = distributorService.getDistributorById(id);
		return distributor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	  @PostMapping("/login")
	    public ResponseEntity<Distributor> login(@RequestBody LoginRequest request) {
		  Distributor distributor = distributorService.login(request.getEmail(), request.getPassword());
	        return ResponseEntity.ok(distributor	);
	    }
	  
    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDistributor(@PathVariable String id) {
    	distributorService.deleteDistributor(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
    
    @GetMapping("/stock/{distributorId}")
    public ResponseEntity<List<DistributorStockDTO>> getDistributorStock(@PathVariable String distributorId) {
        return ResponseEntity.ok(productRequestService.getCurrentStockByDistributor(distributorId));
    }
}

