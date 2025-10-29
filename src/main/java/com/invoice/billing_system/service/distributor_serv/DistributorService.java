package com.invoice.billing_system.service.distributor_serv;

import com.invoice.billing_system.model.admin.Admin;
import com.invoice.billing_system.model.distributor.Distributor;
import com.invoice.billing_system.repository.distributor_repo.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DistributorService {

    @Autowired
    private DistributorRepository distributorRepository;
    
    @Autowired
	private JdbcTemplate jdbcTemplate;

    public Distributor saveDistributor(Distributor distributor) {
//        String newId = generateDistributorId();
//        distributor.setId(newId);
//        distributor.setCreatedDate(LocalDate.now());
//        return distributorRepository.save(distributor);
    	
    	if (distributor.getId() == null || distributor.getId().isEmpty()) {
			Long nextVal = jdbcTemplate.queryForObject("SELECT nextval('distributor_id_seq')", Long.class);
			String formattedId = String.format("DIST%05d", nextVal);
			distributor.setId(formattedId);
			distributor.setCreatedDate(LocalDate.now());
		}
		return distributorRepository.save(distributor);
    }
    
    
    
    public List<Distributor> getAllDistributors() {
        return distributorRepository.findAll();
    }

    
    public Optional<Distributor> getDistributorById(String id) {
		return distributorRepository.findById(id);
	}

    

    public void deleteDistributor(String id) {
        distributorRepository.deleteById(id);
    }
    
    public Distributor login(String email, String password) {
    	Distributor admin = distributorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Distributor not found with email: " + email));

        if (!admin.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return admin;
    }
}

