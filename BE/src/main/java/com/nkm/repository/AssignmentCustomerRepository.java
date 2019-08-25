package com.nkm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkm.entity.AssignmentCustomerEntity;

public interface AssignmentCustomerRepository extends JpaRepository<AssignmentCustomerEntity, Long>{
	
}
