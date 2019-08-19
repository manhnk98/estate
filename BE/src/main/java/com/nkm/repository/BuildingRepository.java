package com.nkm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nkm.entity.BuildingEntity;
import com.nkm.repository.custom.BuildingRepositoryCustome;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustome {
	@Modifying
	@Query(value = "delete from assignmentbuilding where building_id = ?1", nativeQuery = true)
	@Transactional
	void deleteAss(Long buildingId);
	
	@Modifying
	@Query(value = "insert into assignmentbuilding (building_id, user_id) values (?1, ?2)", nativeQuery = true)
	@Transactional
	void handoverBuilding(Long buildingId, Long userId);
}

