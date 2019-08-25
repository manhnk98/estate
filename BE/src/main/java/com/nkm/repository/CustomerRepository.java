package com.nkm.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nkm.entity.AssignmentCustomerEntity;
import com.nkm.entity.CustomerEntity;
import com.nkm.repository.custom.CustomerRepositoryCustome;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomerRepositoryCustome{
	
	@Transactional
	@Query(value = "delete from assignmentcustomer where customer_id = ?1", nativeQuery = true)
	@Modifying
	void deleteByCustomer_Id(Long customerId);

	@Transactional
	@Query(value = "insert into assignmentcustomer (customer_id, user_id, createddate) values (?1, ?2, ?3)", nativeQuery = true)
	@Modifying
	void handoverCustomer(Long customerId, Long userId, Date date);
	
}
