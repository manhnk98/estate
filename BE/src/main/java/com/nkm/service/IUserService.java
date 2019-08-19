package com.nkm.service;

import java.util.List;

import com.nkm.builder.UserSearchBuilder;
import com.nkm.dto.UserDTO;

public interface IUserService {
	List<UserDTO> getAllStaff(UserSearchBuilder userSearchBuilder);
}
