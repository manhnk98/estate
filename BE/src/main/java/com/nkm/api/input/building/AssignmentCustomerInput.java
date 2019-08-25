package com.nkm.api.input.building;

public class AssignmentCustomerInput {
	private Long[] userIds;
	private Long customerId;
	
	public Long[] getUserIds() {
		return userIds;
	}
	public void setUserIds(Long[] userIds) {
		this.userIds = userIds;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	
}
