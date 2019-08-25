package com.nkm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkm.builder.UserSearchBuilder;
import com.nkm.converter.UserConverter;
import com.nkm.dto.UserDTO;
import com.nkm.entity.UserEntity;
import com.nkm.repository.UserRepository;
import com.nkm.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Override
	public List<UserDTO> getAllStaff(UserSearchBuilder userSearchBuilder) {
		List<UserEntity> userEntities = userRepository.getAllStaff(userSearchBuilder);
		List<UserDTO> results = new ArrayList<>();		
		for (UserEntity userEntity : userEntities) {
			UserDTO userDTO = userConverter.convertToDTO(userEntity);
			boolean rs = false;
			if (userSearchBuilder.getBuildingId() != null) {
				// building
				rs = userRepository.existsByIdAndBuildingsId(userEntity.getId(), Long.parseLong(userSearchBuilder.getBuildingId()));
			} else if (userSearchBuilder.getCustomerId() != null) {
				// customer
				rs = userRepository.existsByIdAndCustomersId(userEntity.getId(), Long.parseLong(userSearchBuilder.getCustomerId()));
			}
			if (rs) {
				userDTO.setChecked("checked");
			}
			results.add(userDTO);
		}
		return results;
	}
}
