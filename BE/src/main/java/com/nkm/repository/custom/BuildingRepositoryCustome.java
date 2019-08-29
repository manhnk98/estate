package com.nkm.repository.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nkm.api.output.building.ListBuildingAndCount;
import com.nkm.builder.BuildingSearchBuilder;
import com.nkm.entity.BuildingEntity;

public interface BuildingRepositoryCustome {
	ListBuildingAndCount findAll(BuildingSearchBuilder builder, Pageable pageable);
	Long count(BuildingSearchBuilder builder);
	void deleteBuilding(Long id);
}
