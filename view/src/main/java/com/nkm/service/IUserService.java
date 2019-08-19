package com.nkm.service;

import com.nkm.dto.UserDTO;
import com.nkm.entity.UserEntity;

import java.util.List;

public interface IUserService {
	UserEntity save(UserDTO newUser);
	List<UserDTO> getListStaff(String url);
	List<UserDTO> findAll(String url);
}
