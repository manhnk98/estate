package com.nkm.repository;

import com.nkm.entity.UserEntity;

public interface IUserRepository {
	Long insert(UserEntity userEntity);
}
