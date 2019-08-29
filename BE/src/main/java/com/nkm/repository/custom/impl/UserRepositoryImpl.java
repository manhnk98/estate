package com.nkm.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nkm.builder.UserSearchBuilder;
import com.nkm.entity.UserEntity;
import com.nkm.repository.custom.UserRepositoryCustome;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustome {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> getAllStaff(UserSearchBuilder userSearchBuilder) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT u.* FROM user as u INNER JOIN user_role as ur ON ur.user_id = u.id ");
		sql.append(" INNER JOIN role as r ON ur.role_id = r.id WHERE r.code = :role and u.status = 1 ");
		Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
		query.setParameter("role", userSearchBuilder.getRole());
		List<UserEntity> rs = query.getResultList();
		return rs;
	}
}
