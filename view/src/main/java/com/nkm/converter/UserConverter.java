package com.nkm.converter;

import org.modelmapper.ModelMapper;

import com.nkm.dto.UserDTO;
import com.nkm.entity.UserEntity;

public class UserConverter {
	public UserDTO convertToDTO(UserEntity userEntity) {
		ModelMapper modelMapper = new ModelMapper();
		UserDTO dto = modelMapper.map(userEntity, UserDTO.class);
		return dto;
	}
	
	public UserEntity convertToEntity(UserDTO userDTO) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity buildingEntity = modelMapper.map(userDTO, UserEntity.class);
		return buildingEntity;
	}
}
