package com.nkm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nkm.api.input.building.AssignmentBuildingInput;
import com.nkm.api.output.building.ListBuildingAndCount;
import com.nkm.builder.BuildingSearchBuilder;
import com.nkm.dto.BuildingDTO;

public interface IBuildingService {
	BuildingDTO save(BuildingDTO newBuilding);
	ListBuildingAndCount findAll(BuildingSearchBuilder builder, Pageable pageable);
	int count(BuildingSearchBuilder builder);
	BuildingDTO findById(Long id);
//	void update(BuildingDTO buildingUpdate);
	void deleteBuilding(Long[] ids);
	void handoverBuilding(AssignmentBuildingInput ass);
}
