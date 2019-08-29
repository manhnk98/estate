package com.nkm.api;

import java.util.List;

import com.nkm.dto.BuildingDTO;
import com.nkm.entity.BuildingEntity;

public class ListBuildingAndCount {
	
	List<BuildingEntity> buildingEntities;
	Long count;
	List<BuildingDTO> buildingDtos;
	
	public List<BuildingEntity> getBuildingEntities() {
		return buildingEntities;
	}
	public void setBuildingEntities(List<BuildingEntity> buildingEntities) {
		this.buildingEntities = buildingEntities;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<BuildingDTO> getBuildingDtos() {
		return buildingDtos;
	}
	public void setBuildingDtos(List<BuildingDTO> buildingDtos) {
		this.buildingDtos = buildingDtos;
	}
	
	
}
