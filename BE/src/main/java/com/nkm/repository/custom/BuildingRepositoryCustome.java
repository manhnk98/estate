package com.nkm.repository.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nkm.builder.BuildingSearchBuilder;
import com.nkm.entity.BuildingEntity;

public interface BuildingRepositoryCustome {
	List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageable pageable);
	Long count(BuildingSearchBuilder builder);
	void deleteBuilding(Long id);
}
