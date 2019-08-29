package com.nkm.repository.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nkm.api.output.customer.ListCustomerAndCount;
import com.nkm.builder.CustomerSearchBuilder;
import com.nkm.entity.AssignmentCustomerEntity;

public interface CustomerRepositoryCustome {
	ListCustomerAndCount findAll(CustomerSearchBuilder builder, Pageable pageable, Long staffId);
	Long count(CustomerSearchBuilder builder);
	void deleteCustomer(Long id);
	List<AssignmentCustomerEntity> findAllByKeyAndId(Long id);
}
