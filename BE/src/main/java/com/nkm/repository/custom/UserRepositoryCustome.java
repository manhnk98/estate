package com.nkm.repository.custom;

import java.util.List;

import com.nkm.builder.UserSearchBuilder;
import com.nkm.entity.UserEntity;

public interface UserRepositoryCustome {
	List<UserEntity> getAllStaff(UserSearchBuilder userSearchBuilder);
}
