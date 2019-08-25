package com.nkm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkm.entity.UserEntity;
import com.nkm.repository.custom.UserRepositoryCustome;

public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustome{
	boolean existsByIdAndBuildingsId(Long userId, Long buildingId);
	boolean existsByIdAndCustomersId(Long id, Long parseLong);
}
