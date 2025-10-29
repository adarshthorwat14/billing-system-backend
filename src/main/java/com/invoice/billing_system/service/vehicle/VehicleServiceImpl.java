package com.invoice.billing_system.service.vehicle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.billing_system.model.delivery.DeliveryNote;
import com.invoice.billing_system.model.dispatch_dto.ApprovedVehicleRequestDTO;
import com.invoice.billing_system.model.distributor.ProductRequest;
import com.invoice.billing_system.model.distributor.ProductRequestItem;
import com.invoice.billing_system.model.distributor.distributor_dto.ProductRequestItemDTO;
import com.invoice.billing_system.model.logistic.LogisticVehicle;
import com.invoice.billing_system.model.notification.Notification;
import com.invoice.billing_system.model.vehicle.VehicleRequestPlant;
import com.invoice.billing_system.repository.delivery.DeliveryNoteRepository;
import com.invoice.billing_system.repository.distributor_repo.ProductRequestRepository;
import com.invoice.billing_system.repository.logistic_repo.LogisticVehicleRepository;
import com.invoice.billing_system.repository.notification.NotificationRepository;
import com.invoice.billing_system.repository.vehicle.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRequestRepository;

    private final DeliveryNoteRepository deliveryNoteRepository;
    
    @Autowired
   private ProductRequestRepository productRequestRepository;
    
    @Autowired
	private NotificationRepository notificationRepository;
    
    @Autowired
   private LogisticVehicleRepository vehicleRepo;
   
    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRequestRepository,DeliveryNoteRepository deliveryNoteRepository) {
        this.vehicleRequestRepository = vehicleRequestRepository;
        this.deliveryNoteRepository = deliveryNoteRepository;
    }

    @Override
    public VehicleRequestPlant requestVehicle(VehicleRequestPlant request) {
        // Generate transport ID
        String transportId = "TR/" + request.getRequestId() + "/" + request.getVehicleNumber();
        request.setTransportId(transportId);
        request.setStatus("FORWARDED");
        
        

        // Save the vehicle request
        VehicleRequestPlant savedRequest = vehicleRequestRepository.save(request);

        // ✅ Update DeliveryNote status to "PROCESS"
        List<DeliveryNote> notes = deliveryNoteRepository.findByRequestId(request.getRequestId());

        if (!notes.isEmpty()) {
            for (DeliveryNote note : notes) {
                note.setStatus("PROCESS");   // ✅ status field exists in your entity
                deliveryNoteRepository.save(note);
            }
        }
        
        
        LogisticVehicle vehicle = vehicleRepo.findByVehicleNumber(request.getVehicleNumber())
                .orElseThrow(() -> new RuntimeException("Vehicle not found" + request.getVehicleNumber() ));

        vehicle.setStatus("BOOKED"); // or any other status you need
        vehicleRepo.save(vehicle);
        
        // 🔔 Create notification for distributor
        Notification notification = new Notification();
        notification.setUserRole("PLANT");
        notification.setRecipientId(request.getRequestId());
        notification.setMessage("Plant Manager has sent the vehicle request Vehicle Number is : " + request.getVehicleNumber() +" | " +" Driver name is :" + request.getDriverName());
        notification.setRead(false);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);
        
        return savedRequest;
    }

    @Override
    public List<VehicleRequestPlant> getAllRequests() {
        return vehicleRequestRepository.findAll();
    }

    @Override
    public List<VehicleRequestPlant> getAllPendingRequests() {
        return vehicleRequestRepository.findAll()
                .stream()
                .filter(request -> !"APPROVED".equalsIgnoreCase(request.getStatus()))
                .collect(Collectors.toList());
    }
    
   
    @Override
    public void approveRequest(String transportId) {
        VehicleRequestPlant req = vehicleRequestRepository.findByTransportId(transportId)
            .orElseThrow(() -> new RuntimeException("Request not found"));
        req.setStatus("APPROVED");
        vehicleRequestRepository.save(req);
        
        Notification notification = new Notification();
        notification.setUserRole("VEHICLE DPT");
        notification.setRecipientId(req.getRequestId());
        notification.setMessage("Your vehicle request for : "+req.getRequestId()+" is approved by Vehicle Department ");
        notification.setRead(false);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);
    }
    
    
    @Override
    public List<ApprovedVehicleRequestDTO> getApprovedRequests() {
        List<VehicleRequestPlant> approvedRequests = vehicleRequestRepository.findByStatus("APPROVED");

        return approvedRequests.stream()
        	    .filter(vr -> {
        	        List<DeliveryNote> notes = deliveryNoteRepository.findByRequestId(vr.getRequestId());
        	        return notes != null && notes.stream().anyMatch(n -> !"DISPATCHED".equalsIgnoreCase(n.getStatus()));
        	    })
        	    .map(vr -> {
        	        ApprovedVehicleRequestDTO dto = new ApprovedVehicleRequestDTO();
        	        dto.setRequestId(vr.getRequestId());
        	        dto.setVehicleNumber(vr.getVehicleNumber());
        	        dto.setDriverName(vr.getDriverName());
        	        // dto.setDriverPhoneNumber(vr.getDriverPhoneNumber()); // if available

        	        // Pick the first non-dispatched note
        	        List<DeliveryNote> notes = deliveryNoteRepository.findByRequestId(vr.getRequestId());
        	        if (notes != null && !notes.isEmpty()) {
        	            DeliveryNote note = notes.stream()
        	                .filter(n -> !"DISPATCHED".equalsIgnoreCase(n.getStatus()))
        	                .findFirst()
        	                .orElse(notes.get(0)); // fallback to first

        	            dto.setDeliveryNoteId(note.getDeliveryNoteId());
        	            dto.setFromLocation(note.getFromLocation());
        	            dto.setToLocation(note.getToLocation());
        	        }

                // Product request
                ProductRequest pr = productRequestRepository.findById(vr.getRequestId()).orElse(null);
                if (pr != null && pr.getRequestItems() != null) {
                    List<ProductRequestItemDTO> itemDTOs = pr.getRequestItems().stream().map(item -> {
                        ProductRequestItemDTO itemDTO = new ProductRequestItemDTO();
                        itemDTO.setProductId(item.getProduct().getProductId());
                        itemDTO.setProductName(item.getProduct().getName());
                        itemDTO.setQuantityRequested(item.getQuantityRequested());
                        itemDTO.setUnit(item.getUnit());
                        return itemDTO;
                    }).collect(Collectors.toList());
                    dto.setRequestItems(itemDTOs);
                }

                return dto;
            })
            .collect(Collectors.toList());
    }

	@Override
	public List<VehicleRequestPlant> getAllApprovedRequests() {
		List<VehicleRequestPlant> approvedRequests = vehicleRequestRepository.findByStatus("APPROVED");
		return approvedRequests;
	}

    

}

