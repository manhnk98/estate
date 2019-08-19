package com.nkm.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.nkm.dto.BuildingDTO;
import com.nkm.entity.BuildingEntity;
import com.nkm.entity.RentAreaEntity;

@Component
public class BuildingConverter {
	
	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingDTO dto = modelMapper.map(buildingEntity, BuildingDTO.class);
		
		List<String> areas = buildingEntity.getAreas().stream()
				.map(RentAreaEntity::getValue)
				.map(item -> item.toString())
				.collect(Collectors.toList());
		
		if(areas.size() > 0) {
			dto.setRentArea(StringUtils.join(areas,","));
		}
		
		if (StringUtils.isNotBlank(buildingEntity.getType())) {
			dto.setBuildingTypes(buildingEntity.getType().split(","));
		}
		return dto;
	}
	
	public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
		if (StringUtils.isNotBlank(buildingDTO.getNumberOfBasement())) {
			buildingEntity.setNumberOfBasement(Integer.parseInt(buildingDTO.getNumberOfBasement()));
		}
		if (StringUtils.isNotBlank(buildingDTO.getBuildingArea())) {
			buildingEntity.setBuildingArea(Integer.parseInt(buildingDTO.getBuildingArea()));
		}
		return buildingEntity;
	}
}
