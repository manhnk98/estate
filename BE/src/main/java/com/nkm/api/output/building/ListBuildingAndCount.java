package com.nkm.api.output.building;

import java.util.List;

import com.nkm.entity.BuildingEntity;

public class ListBuildingAndCount {
	
	List<BuildingEntity> buildings;
	Long count;
	public List<BuildingEntity> getBuildings() {
		return buildings;
	}
	public void setBuildings(List<BuildingEntity> buildings) {
		this.buildings = buildings;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
