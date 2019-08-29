package com.nkm.api;

import java.util.List;

import com.nkm.dto.CustomerDTO;
import com.nkm.entity.CustomerEntity;

public class ListCustomerAndCount {
	
	List<CustomerEntity> customerEntities;
	Long count;
	List<CustomerDTO> customerDtos;
	public List<CustomerEntity> getCustomerEntities() {
		return customerEntities;
	}
	public void setCustomerEntities(List<CustomerEntity> customerEntities) {
		this.customerEntities = customerEntities;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<CustomerDTO> getCustomerDtos() {
		return customerDtos;
	}
	public void setCustomerDtos(List<CustomerDTO> customerDtos) {
		this.customerDtos = customerDtos;
	}
	
	
	
}
