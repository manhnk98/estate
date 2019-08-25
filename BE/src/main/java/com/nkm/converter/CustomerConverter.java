package com.nkm.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.nkm.dto.CustomerDTO;
import com.nkm.entity.CustomerEntity;

@Component
public class CustomerConverter {
	
	public CustomerDTO convertToDTO(CustomerEntity customerEntity) {
		ModelMapper modelMapper = new ModelMapper();
		CustomerDTO dto = modelMapper.map(customerEntity, CustomerDTO.class);
		return dto;
	}
	
	public CustomerEntity convertToEntity(CustomerDTO customerDTO) {
		ModelMapper modelMapper = new ModelMapper();
		CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
		return customerEntity;
	}
}
