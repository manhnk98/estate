package com.nkm.repository.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nkm.builder.CustomerSearchBuilder;
import com.nkm.entity.AssignmentCustomerEntity;
import com.nkm.entity.CustomerEntity;

public interface CustomerRepositoryCustome {
	List<CustomerEntity> findAll(CustomerSearchBuilder builder, Pageable pageable, Long staffId);
	Long count(CustomerSearchBuilder builder);
	void deleteCustomer(Long id);
	List<AssignmentCustomerEntity> findAllByKeyAndId(Long id);
}
