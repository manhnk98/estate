package com.nkm.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.nkm.dto.AssignmentCustomerDTO;
import com.nkm.entity.AssignmentCustomerEntity;

@Component
public class AssignmentCustomerConverter {
	public AssignmentCustomerDTO convertToDTO(AssignmentCustomerEntity assEntity) {
		ModelMapper modelMapper = new ModelMapper();
		AssignmentCustomerDTO dto = modelMapper.map(assEntity, AssignmentCustomerDTO.class);
		return dto;
	}
	
	public AssignmentCustomerEntity convertToEntity(AssignmentCustomerDTO assDto) {
		ModelMapper modelMapper = new ModelMapper();
		AssignmentCustomerEntity assEntity = modelMapper.map(assDto, AssignmentCustomerEntity.class);
		return assEntity;
	}
}
