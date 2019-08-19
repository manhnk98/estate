package com.nkm.repository.impl;

import com.nkm.entity.UserEntity;
import com.nkm.repository.IUserRepository;

public class UserRepository extends AbstractJDBC<UserEntity> implements IUserRepository{

	@Override
	public Long insert(UserEntity userEntity) {
//		StringBuilder sql = new StringBuilder("INSERT INTO ");
		return null;
	}

}
