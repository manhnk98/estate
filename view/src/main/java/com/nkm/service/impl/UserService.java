package com.nkm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkm.converter.UserConverter;
import com.nkm.dto.UserDTO;
import com.nkm.entity.UserEntity;
import com.nkm.service.IUserService;
import com.nkm.utils.HttpClientUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService implements IUserService{

	final static Logger logger = Logger.getLogger(UserService.class);

	@Override
	public UserEntity save(UserDTO newUser) {
		UserConverter userConverter = new UserConverter();
		UserEntity userEntity = userConverter.convertToEntity(newUser);
		return userEntity;
	}

	@Override
	public List<UserDTO> getListStaff(String url) {
		String result = HttpClientUtils.httpGet(url);
		try {
			return Arrays.asList(new ObjectMapper().readValue(result, UserDTO[].class));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<>();
	}

	@Override
	public List<UserDTO> findAll(String url) {
		String result = HttpClientUtils.httpGet(url);
		try {
			List<UserDTO> rs = Arrays.asList(new ObjectMapper().readValue(result, UserDTO[].class));
			return rs;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<>();
	}

}
