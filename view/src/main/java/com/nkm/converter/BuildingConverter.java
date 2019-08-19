package com.nkm.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;

import com.nkm.dto.BuildingDTO;
import com.nkm.entity.BuildingEntity;
import com.nkm.entity.RentAreaEntity;
import com.nkm.paging.PageRequest;
import com.nkm.repository.IRentAreaRepository;
import com.nkm.repository.impl.RentAreaRepository;

public class BuildingConverter {
	
//	@Inject
	private IRentAreaRepository rentAreaRepository = new RentAreaRepository();
	
	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingDTO dto = modelMapper.map(buildingEntity, BuildingDTO.class);
		
		Map<String, Object> condition = new HashMap<>();
		condition.put("buildingid", buildingEntity.getId());
		// java 7
//		List<RentAreaEntity> rentAreas = rentAreaRepository.findAllAbs(condition, new PageRequest(null, null, null));
//		List<String> areas = new ArrayList<>();
//		for(RentAreaEntity entity : rentAreas) {
//			areas.add(entity.getValue());
//		}
//		if(areas.size() > 0) {
//			dto.setRentArea(StringUtils.join(areas,","));
//		}
		// java 8
		List<String> areas = rentAreaRepository.findAllAbs(condition, new PageRequest(null, null, null))
				.stream().map(RentAreaEntity::getValue).collect(Collectors.toList());
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
