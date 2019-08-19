package com.nkm.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.nkm.enums.BuildingTypeEnum;
import com.nkm.enums.DistrictEnum;

public class DataUltil {
	public static Map<String, String> getBuildingTypes(){
		Map<String, String> results = new HashMap<>();
		Stream.of(BuildingTypeEnum.values()).forEach(item -> {
			results.put(item.name(), item.getValue());
		});
		return results;
	}
	
	
	public static Map<String, String> getDistricts(){
		Map<String, String> results = new HashMap<>();
		// java 8
//		Stream.of(DistrictEnum.values()).forEach(item -> {
//			results.put(item.name(), item.getValue());
//		});
		for(DistrictEnum item : DistrictEnum.values()) {
			results.put(item.name(), item.getValue());
		}
		return results;
	}
}
