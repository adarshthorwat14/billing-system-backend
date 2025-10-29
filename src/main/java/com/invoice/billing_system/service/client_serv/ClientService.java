package com.invoice.billing_system.service.client_serv;

import com.invoice.billing_system.model.client.Client;
import com.invoice.billing_system.model.client_dto.ClientDTO;
import com.invoice.billing_system.model.distributor.Distributor;
import com.invoice.billing_system.repository.client_repo.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Create or Update a Client
	public Client saveClient(Client client) {
		if (client.getId() == null || client.getId().isEmpty()) {
			Long nextVal = jdbcTemplate.queryForObject("SELECT nextval('client_id_seq')", Long.class);
			String formattedId = String.format("CL%05d", nextVal);
			client.setId(formattedId);
		}
		return clientRepository.save(client);
	}

	// Get all Clients
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	// Get a Client by ID
	public Optional<Client> getClientById(String id) {
		return clientRepository.findById(id);
	}
	
	 public Optional<Client> updateClient(String id, Client updatedClient) {
	        return clientRepository.findById(id).map(existingClient -> {
	            existingClient.setName(updatedClient.getName());
	            existingClient.setEmail(updatedClient.getEmail());
	            existingClient.setPhone(updatedClient.getPhone());
	            existingClient.setAddress(updatedClient.getAddress());
	            existingClient.setPassword(updatedClient.getPassword());
	            existingClient.setCity(updatedClient.getCity());
	            existingClient.setPincode(updatedClient.getPincode());
	            existingClient.setDistrict(updatedClient.getDistrict());
	            existingClient.setState(updatedClient.getState());
	            existingClient.setPancard(updatedClient.getPancard());
	            existingClient.setGst(updatedClient.getGst());
	            existingClient.setAdharCard(updatedClient.getAdharCard());
	            existingClient.setStatus(updatedClient.isStatus());
	            return clientRepository.save(existingClient);
	        });
	    }

	// Delete a Client by ID
	public void deleteClient(String id) {
		clientRepository.deleteById(id);
	}
	
	public List<Client> getClientsByDistributorId(String distributorId) {
	    return clientRepository.findByDistributor(distributorId);
	}
	
	public Client login(String id,String password) {
		Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        if (!client.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return client;
    }
	
	public List<ClientDTO> getAllClientsWithDistributors() {
	    List<Client> clients = clientRepository.findAll();
	    return clients.stream().map(client -> {
	        ClientDTO dto = new ClientDTO();
	        dto.setId(client.getId());
	        dto.setName(client.getName());
	        dto.setEmail(client.getEmail());
	        dto.setPhone(client.getPhone());
	        dto.setAddress(client.getAddress());
	        dto.setAdharCard(client.getAdharCard());
	        dto.setCity(client.getCity());
	        dto.setDistrict(client.getDistrict());
	        dto.setGst(client.getGst());
	        dto.setPancard(client.getPancard());
	        dto.setPincode(client.getPincode());
	        dto.setState(client.getState());
	       
	        dto.setStatus(client.isStatus());

	        return dto;
	    }).collect(Collectors.toList());
	}

}
