package com.nkm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nkm.api.input.building.AssignmentCustomerInput;
import com.nkm.builder.CustomerSearchBuilder;
import com.nkm.converter.AssignmentCustomerConverter;
import com.nkm.converter.CustomerConverter;
import com.nkm.dto.AssignmentCustomerDTO;
import com.nkm.dto.CustomerDTO;
import com.nkm.entity.AssignmentCustomerEntity;
import com.nkm.entity.CustomerEntity;
import com.nkm.repository.AssignmentCustomerRepository;
import com.nkm.repository.CustomerRepository;
import com.nkm.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerConverter customerConverter;
	
	@Autowired
	private AssignmentCustomerConverter assignmentCustomerConverter;
	
	@Autowired AssignmentCustomerRepository assignmentCustomerRepository;

	@Override
	public CustomerDTO save(CustomerDTO customerDTO) {
		CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
		if (customerDTO.getId() != null) {
			// update
			CustomerEntity oldCustomer = customerRepository.findById(customerDTO.getId()).get();
			customerEntity.setCreatedBy(oldCustomer.getCreatedBy());
			customerEntity.setCreatedDate(oldCustomer.getCreatedDate());
			customerEntity.setModifiedDate(new Date());
		}else {
			customerEntity.setCreatedDate(new Date());
			customerEntity.setCreatedBy("ManhNK");
		}
		customerEntity = customerRepository.save(customerEntity);
		return customerConverter.convertToDTO(customerEntity);
	}

	@Override
	public List<CustomerDTO> findAll(CustomerSearchBuilder builder, Pageable pageable, Long staffId) {
		List<CustomerEntity> lstEntities = customerRepository.findAll(builder, pageable, staffId);
		List<CustomerDTO> lstDtos = lstEntities.stream().map(item -> customerConverter.convertToDTO(item)).collect(Collectors.toList());
		return lstDtos;
	}

	@Override
	public CustomerDTO findById(Long id) {
		CustomerEntity entity = customerRepository.findById(id).get();
		return customerConverter.convertToDTO(entity);
	}

	@Override
	public void deleteCustomers(Long[] ids) {
		
	}

	@Override
	public int count(CustomerSearchBuilder builder) {
		return customerRepository.count(builder).intValue();
	}

	@Override
	public void handoverBuilding(AssignmentCustomerInput ass) {
		Long customerId = ass.getCustomerId();
		customerRepository.deleteByCustomer_Id(customerId);
		for (Long userId : ass.getUserIds()) {
			customerRepository.handoverCustomer(customerId, userId, new Date());
		}
	}

	@Override
	public List<AssignmentCustomerDTO> findAllByKeyAndId(Long id) {
		List<AssignmentCustomerEntity> entities = customerRepository.findAllByKeyAndId(id);
		List<AssignmentCustomerDTO> lstDtos = entities.stream().map(item -> assignmentCustomerConverter.convertToDTO(item)).collect(Collectors.toList());
		return lstDtos;
	}

	@Override
	public void activityUpdate(AssignmentCustomerDTO dto) {
		AssignmentCustomerEntity entity = assignmentCustomerConverter.convertToEntity(dto);
		entity.setCreatedDate(new Date());
		assignmentCustomerRepository.save(entity);
	}


}
