package com.invoice.billing_system.model.distributor.distributor_dto;

import java.util.List;

public class ApprovalRequestDTO {

	 private String status;
	 private String remark;
	 private List<DistributorStockDTO> plantApprovals;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<DistributorStockDTO> getPlantApprovals() {
		return plantApprovals;
	}
	public void setPlantApprovals(List<DistributorStockDTO> plantApprovals) {
		this.plantApprovals = plantApprovals;
	}
	 
	 
}
