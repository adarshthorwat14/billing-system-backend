package com.invoice.billing_system.controller.client;

import com.invoice.billing_system.model.admin_dto.LoginRequest;
import com.invoice.billing_system.model.client.Client;
import com.invoice.billing_system.model.client_dto.ClientDTO;
import com.invoice.billing_system.model.distributor.Distributor;
import com.invoice.billing_system.repository.client_repo.ClientRepository;
import com.invoice.billing_system.service.client_serv.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
	
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getClientCount() {
        long count = clientRepository.count();
        return ResponseEntity.ok(Collections.singletonMap("count", count));
    }
    
	// Create or Update a Client
	@PostMapping
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		Client savedClient = clientService.saveClient(client);
		return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ClientDTO>> getAllClientsWithDistributors() {
	    List<ClientDTO> dtos = clientService.getAllClientsWithDistributors();
	    return ResponseEntity.ok(dtos);
	}

	// Get all Clients
//	@GetMapping
//	public ResponseEntity<List<Client>> getAllClients() {
//		List<Client> clients = clientService.getAllClients();
//		return new ResponseEntity<>(clients, HttpStatus.OK);
//	}

	// Get a Client by ID
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable String id) {
		Optional<Client> client = clientService.getClientById(id);
		return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable String id, @RequestBody Client updatedClient) {
	    return clientService.updateClient(id, updatedClient)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	}

	// Delete a Client by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable String id) {
		clientService.deleteClient(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	  @PostMapping("/login")
	    public ResponseEntity<Client> login(@RequestBody LoginRequest request) {
		  Client client = clientService.login(request.getId(),request.getPassword());
	        return ResponseEntity.ok(client	);
	    }
	
	@GetMapping("/by-distributor/{distributorId}")
	public ResponseEntity<List<Client>> getClientsByDistributor(@PathVariable String distributorId) {
	    List<Client> clients = clientService.getClientsByDistributorId(distributorId);
	    return ResponseEntity.ok(clients);
	}
}
