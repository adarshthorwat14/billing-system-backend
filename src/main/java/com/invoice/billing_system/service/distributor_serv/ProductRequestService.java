package com.invoice.billing_system.service.distributor_serv;



import com.invoice.billing_system.model.delivery_dto.ProductRequestWithDeliveryNoteDTO;
import com.invoice.billing_system.model.distributor.ProductRequest;
import com.invoice.billing_system.model.distributor.ProductRequestItem;
import com.invoice.billing_system.model.distributor.distributor_dto.DistributorStockDTO;
import com.invoice.billing_system.model.distributor.distributor_dto.ProductRequestDTO;
import com.invoice.billing_system.model.product_dto.ProductRequestWithStockDTO;

import java.util.List;
import java.util.Map;

public interface ProductRequestService {
    ProductRequest createRequest(ProductRequestDTO requestDTO);
//    List<ProductRequest> getRequestsByDistributor(String distributorId);
    List<ProductRequestDTO> getAllProductRequests();
    
//    ProductRequestWithDeliveryNoteDTO updateRequestStatus(
//            String requestId, 
//            String status, 
//            String remark, 
//            Map<String, DistributorStockDTO> plantApprovals
//    );
	List<ProductRequestDTO> getRequestsByDistributor(String distributorId);
//	ProductRequestWithStockDTO getRequestWithStockDetails(String requestId);
	List<DistributorStockDTO> getCurrentStockByDistributor(String distributorId);
	 List<ProductRequest> findApprovedRequestsByDistributorId(String distributorId);
	 
	ProductRequestWithDeliveryNoteDTO updateRequestStatus(String requestId, String status, String remark,
			List<DistributorStockDTO> plantApprovals);
	

}
