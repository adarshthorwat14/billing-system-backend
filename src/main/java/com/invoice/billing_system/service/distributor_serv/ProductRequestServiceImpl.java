package com.invoice.billing_system.service.distributor_serv;

import com.invoice.billing_system.model.delivery.DeliveryNote;
import com.invoice.billing_system.model.delivery_dto.ProductRequestWithDeliveryNoteDTO;
import com.invoice.billing_system.model.distributor.Distributor;
import com.invoice.billing_system.model.distributor.ProductRequest;
import com.invoice.billing_system.model.distributor.ProductRequestItem;
import com.invoice.billing_system.model.distributor.distributor_dto.ProductRequestItemDTO;
import com.invoice.billing_system.model.notification.Notification;
import com.invoice.billing_system.model.plant.PlantStock;
import com.invoice.billing_system.model.product.Product;
import com.invoice.billing_system.model.product_dto.ProductRequestWithStockDTO;
import com.invoice.billing_system.model.product_dto.ProductStockView;
import com.invoice.billing_system.model.distributor.distributor_dto.DistributorStockDTO;
import com.invoice.billing_system.model.distributor.distributor_dto.ProductRequestDTO;
import com.invoice.billing_system.repository.delivery.DeliveryNoteRepository;
import com.invoice.billing_system.repository.distributor_repo.DistributorRepository;
import com.invoice.billing_system.repository.distributor_repo.ProductRequestRepository;
import com.invoice.billing_system.repository.notification.NotificationRepository;
import com.invoice.billing_system.repository.plant.PlantStockRepository;
import com.invoice.billing_system.repository.product_repo.ProductRepository;
import com.invoice.billing_system.service.distributor_serv.ProductRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class ProductRequestServiceImpl implements ProductRequestService {
		@Autowired
	 	private final ProductRequestRepository productRequestRepository;
		
		@Autowired
		private DeliveryNoteRepository deliveryNoteRepository;
		
		@Autowired
		private NotificationRepository notificationRepository;
		
		private String generateDeliveryNoteId() {
		    String prefix = "DNOTE";
		    long count = deliveryNoteRepository.count() + 1;
		    return prefix + String.format("%05d", count);
		}
		
		@Autowired
		private PlantStockRepository plantStockRepository;
		
	 	@Autowired
	    private final DistributorRepository distributorRepository;
	    @Autowired
	    private final ProductRepository productRepository;
	    
	    public ProductRequestServiceImpl(ProductRequestRepository productRequestRepository, DistributorRepository distributorRepository,ProductRepository productRepository) {
	        this.productRequestRepository = productRequestRepository;
	        this.distributorRepository = distributorRepository;
	        this.productRepository = productRepository;
	    }

	    @Override
	    public ProductRequest createRequest(ProductRequestDTO dto) {
	        // Fetch distributor by ID
	        Distributor distributor = distributorRepository.findById(dto.getDistributorId())
	            .orElseThrow(() -> new RuntimeException("Distributor not found with ID: " + dto.getDistributorId()));

	        // Build main ProductRequest entity
	        ProductRequest request = new ProductRequest();
	        request.setRequestId(generateNextRequestId());
	        request.setDistributor(distributor);
	        request.setRequestDate(dto.getRequestDate());
	        request.setBillingDate(dto.getBillingDate()); // ✅ new field
	        request.setStatus(dto.getStatus());
	        request.setPriority(dto.getPriority());
	        request.setNotes(dto.getNotes());
	        request.setRequestedBy(dto.getRequestedBy());
	        request.setTotalQuantity(dto.getTotalQuantity());
	        request.setTotalItems(dto.getTotalItems());
	        request.setSalesPerson(dto.getSalesPerson());
	        request.setBranch(dto.getBranch());
	        request.setRegion(dto.getRegion());

	        // ✅ New plant & delivery info
	        request.setDeliveryTo(dto.getDeliveryTo());

	        // ✅ New tax & pricing
	        request.setCgst(dto.getCgst());
	        request.setSgst(dto.getSgst());
	        request.setIgst(dto.getIgst());
	        request.setSubTotal(dto.getSubTotal());
	        request.setProductValue(dto.getProductValue());
	        request.setFinalValue(dto.getFinalValue());

	        // ✅ New remarks
	        request.setRemark(dto.getRemark());

	        // Map request items
	        List<ProductRequestItem> items = new ArrayList<>();
	        for (ProductRequestItemDTO itemDto : dto.getRequestItems()) {
	            Product product = productRepository.findById(itemDto.getProductId())
	                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + itemDto.getProductId()));

	            ProductRequestItem item = new ProductRequestItem();
	            item.setProduct(product);
	            item.setQuantityRequested(itemDto.getQuantityRequested());
	            item.setUnit(itemDto.getUnit());
	            item.setStatus(itemDto.getStatus());
	            item.setDescription(itemDto.getDescription());
	            item.setProductRequest(request);

	            items.add(item);
	        }

	        request.setRequestItems(items);

	        return productRequestRepository.save(request);
	    }


	    @Override
	    public List<ProductRequestDTO> getRequestsByDistributor(String distributorId) {
	        List<ProductRequest> requests = productRequestRepository.findByDistributor_Id(distributorId);
	        
	        return requests.stream()
	                       .map(this::convertToDto)
	                       .collect(Collectors.toList());
	    }
	    
	    private String generateNextRequestId() {
	        // Fetch last request ID from repository
	        String lastId = productRequestRepository.findTopByOrderByRequestIdDesc()
	                             .map(ProductRequest::getRequestId)
	                             .orElse("DPR00000"); // default if none

	        int num = Integer.parseInt(lastId.replace("DPR", ""));
	        return String.format("DPR%05d", num + 1);
	    }
	    
	    @Override
	    public List<ProductRequestDTO> getAllProductRequests() {
	        List<ProductRequest> requests = productRequestRepository.findAll();
	        return requests.stream()
	                       .map(this::convertToDto)
	                       .collect(Collectors.toList());
	    }

	    private ProductRequestDTO convertToDto(ProductRequest request) {
	        ProductRequestDTO dto = new ProductRequestDTO();

	        dto.setRequestId(request.getRequestId());
	        dto.setDistributorId(request.getDistributor().getId());
	        dto.setDistributorName(request.getDistributor().getName()); // ✅ Added

	        dto.setRequestDate(request.getRequestDate());
	        dto.setBillingDate(request.getBillingDate());
	        dto.setStatus(request.getStatus());
	        dto.setPriority(request.getPriority());
	        dto.setNotes(request.getNotes());
	        dto.setRequestedBy(request.getRequestedBy());
	        dto.setTotalQuantity(request.getTotalQuantity());
	        dto.setTotalItems(request.getTotalItems());
	        dto.setRemark(request.getRemark());
	        dto.setSalesPerson(request.getSalesPerson());
	        dto.setBranch(dto.getBranch());
	        dto.setRegion(request.getRegion());
	        dto.setDeliveryTo(request.getDeliveryTo());

	        dto.setCgst(request.getCgst());
	        dto.setSgst(request.getSgst());
	        dto.setIgst(request.getIgst());
	        dto.setSubTotal(request.getSubTotal());
	        dto.setProductValue(request.getProductValue());
	        dto.setFinalValue(request.getFinalValue());

	        List<ProductRequestItemDTO> items = request.getRequestItems().stream().map(item -> {
	            ProductRequestItemDTO itemDto = new ProductRequestItemDTO();
	            itemDto.setProductId(item.getProduct().getProductId());
	            itemDto.setProductName(item.getProduct().getName());
	            itemDto.setQuantityRequested(item.getQuantityRequested());
	            itemDto.setUnit(item.getUnit());
	            itemDto.setStatus(item.getStatus());
	            itemDto.setDescription(item.getDescription());
	            return itemDto;
	        }).collect(Collectors.toList());

	        dto.setRequestItems(items);
	        return dto;
	    }

	    
	   
	    @Override
	    public ProductRequestWithDeliveryNoteDTO updateRequestStatus(
	            String requestId,
	            String status,
	            String remark,
	            List<DistributorStockDTO> plantApprovals
	    ) {
	        ProductRequest request = productRequestRepository.findById(requestId)
	                .orElseThrow(() -> new RuntimeException("Request not found"));

	        if (!status.equalsIgnoreCase("APPROVED") && !status.equalsIgnoreCase("REJECTED")) {
	            throw new RuntimeException("Invalid status");
	        }

	        request.setStatus(status);
	        request.setRemark(remark);

	        List<DeliveryNote> generatedNotes = new ArrayList<>();

	        if (status.equalsIgnoreCase("APPROVED")) {
	            for (DistributorStockDTO approval : plantApprovals) {
	                // 1. Find plant stock
	                PlantStock stock = plantStockRepository
	                        .findByProduct_ProductIdAndPlant_PlantId(approval.getProductId(), approval.getPlantId())
	                        .orElseThrow(() -> new RuntimeException(
	                                "No stock for product " + approval.getProductId() + " in plant " + approval.getPlantId()
	                        ));

	                if (stock.getStockQuantity() < approval.getApprovedQuantity()) {
	                    throw new RuntimeException("Not enough stock in plant " + approval.getPlantName());
	                }

	                // 2. Deduct stock
	                stock.setStockQuantity(stock.getStockQuantity() - approval.getApprovedQuantity());
	                plantStockRepository.save(stock);

	                // 3. Update request item
	                for (ProductRequestItem item : request.getRequestItems()) {
	                    if (item.getProduct().getProductId().equals(approval.getProductId())) {
	                        item.setStatus("APPROVED");
	                        item.setApprovedQuantity(approval.getApprovedQuantity());
	                    }
	                }
	                
	                request.setPlantId(approval.getPlantId());
	                request.setPlantName(approval.getPlantName());
	                request.setDeliveryFrom(request.getDeliveryFrom());
	                // 4. Create delivery note
	                DeliveryNote note = new DeliveryNote();
	                note.setDeliveryNoteId(generateDeliveryNoteId());
	                note.setDate(LocalDate.now());
	                note.setRequestId(request.getRequestId());
	                note.setProductId(approval.getProductId());
	                note.setProductName(approval.getProductName());
	                note.setQuantity(approval.getApprovedQuantity());
	                note.setUnit(approval.getUnit());
	                note.setBranch(request.getBranch());
	                note.setRegion(request.getRegion());
	                note.setDistributorId(request.getDistributor().getId());
	                note.setDistributorName(request.getDistributor().getName());
	                note.setPlantId(approval.getPlantId());     // ✅ include plant
	                note.setPlantName(approval.getPlantName()); // ✅ include plant
	                note.setStatus("DELIVERY_CREATED");
	                note.setFromLocation("BEUNIQUE PLANT - "+ approval.getPlantName());
	                note.setToLocation(request.getDeliveryTo());
	                

	                deliveryNoteRepository.save(note);
	                generatedNotes.add(note);
	            }
	        }

	        productRequestRepository.save(request);

	        ProductRequestWithDeliveryNoteDTO dto = new ProductRequestWithDeliveryNoteDTO();
	        dto.setRequestId(request.getRequestId());
	        dto.setStatus(request.getStatus());
	        dto.setDeliveryNotes(generatedNotes);

	        return dto;
	    }

	    
	    public ProductRequestWithStockDTO getRequestWithStockDetails(String requestId) {
	        ProductRequest request = productRequestRepository.findById(requestId)
	            .orElseThrow(() -> new RuntimeException("Request not found"));

	        ProductRequestDTO requestDto = convertToDto(request);

	        List<ProductStockView> currentStocks = request.getRequestItems().stream().map(item -> {
	            Product product = productRepository.findById(item.getProduct().getProductId())
	                .orElseThrow(() -> new RuntimeException("Product not found"));

	            ProductStockView stockView = new ProductStockView();
	            stockView.setProductId(product.getProductId());
	            stockView.setProductName(product.getName());
//	            stockView.setPlantId(product.getPlant().getPlantId());
//	            stockView.setAvailableStock(product.getStockQuantity());
//	            System.out.println("Product ID: " + product.getProductId());
//	            System.out.println("Plant ID: " + (product.getPlant() != null ? product.getPlant().getPlantId() : "null"));

	            return stockView;
	        }).collect(Collectors.toList());

	        ProductRequestWithStockDTO dto = new ProductRequestWithStockDTO();
	        dto.setRequestDetails(requestDto);
	        dto.setCurrentStocks(currentStocks);
	        return dto;
	    }
	    
	    @Override
	    public List<ProductRequest> findApprovedRequestsByDistributorId(String distributorId) {
	        return productRequestRepository.findApprovedRequestsByDistributorId(distributorId);
	    }

	    @Override
	    public List<DistributorStockDTO> getCurrentStockByDistributor(String distributorId) {
	        List<ProductRequest> approvedRequests = productRequestRepository.findApprovedRequestsByDistributorId(distributorId);

	        Map<String, DistributorStockDTO> stockMap = new HashMap<>();

	        for (ProductRequest request : approvedRequests) {
	            for (ProductRequestItem item : request.getRequestItems()) {
	                if (!"APPROVED".equalsIgnoreCase(item.getStatus())) continue;

	                if (item.getProduct() != null) {
	                    String productId = item.getProduct().getProductId();
	                    String productName = item.getProduct().getName(); // assuming 'name' is the field
	                    String unit = item.getUnit();

	                    stockMap.compute(productId, (key, existing) -> {
	                        if (existing == null) {
	                            DistributorStockDTO dto = new DistributorStockDTO();
	                            dto.setProductId(productId);
	                            dto.setProductName(productName);
	                            dto.setUnit(unit);
	                          
	                            dto.setTotalAllocated(item.getQuantityRequested() - item.getQuantityUsed());
	                            return dto;
	                        } else {
	                            existing.setTotalAllocated(existing.getTotalAllocated() + item.getQuantityRequested());
	                            return existing;
	                        }
	                    });
	                }
	            }
	        }

	        return new ArrayList<>(stockMap.values());
	    }

}
