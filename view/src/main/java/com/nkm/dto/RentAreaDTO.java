package com.nkm.dto;

public class RentAreaDTO extends BaseDTO<RentAreaDTO>{
	private String value;
	private Long buildingId;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	
	
}
