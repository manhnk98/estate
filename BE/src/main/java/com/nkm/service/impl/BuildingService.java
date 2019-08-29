package com.nkm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nkm.api.input.building.AssignmentBuildingInput;
import com.nkm.api.output.building.ListBuildingAndCount;
import com.nkm.builder.BuildingSearchBuilder;
import com.nkm.converter.BuildingConverter;
import com.nkm.dto.BuildingDTO;
import com.nkm.entity.BuildingEntity;
import com.nkm.entity.RentAreaEntity;
import com.nkm.entity.UserEntity;
import com.nkm.repository.BuildingRepository;
import com.nkm.repository.RentAreaRepository;
import com.nkm.service.IBuildingService;

@Service
public class BuildingService implements IBuildingService{
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private BuildingConverter buildingConverter;

	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		if (buildingDTO.getId() != null) {
			// update
			BuildingEntity oldBuilding = buildingRepository.findById(buildingDTO.getId()).get();
			buildingEntity.setCreatedBy(oldBuilding.getCreatedBy());
			buildingEntity.setCreatedDate(oldBuilding.getCreatedDate());
			buildingEntity.setType(StringUtils.join(buildingDTO.getBuildingTypes(), ","));
			rentAreaRepository.deleteByBuildingId(buildingDTO.getId());
		}else {
			//insert
			buildingEntity.setType(StringUtils.join(buildingDTO.getBuildingTypes(), ","));
		}
		List<RentAreaEntity> areas = new ArrayList<>();
		if (buildingDTO.getRentArea().length() > 0) {
			for(String item : buildingDTO.getRentArea().split(",")) {
				RentAreaEntity areaEntity = new RentAreaEntity();
				areaEntity.setValue(Integer.parseInt(item));
				areaEntity.setBuilding(buildingEntity);
				areas.add(areaEntity);
			}
			buildingEntity.setAreas(areas);
		}
		buildingEntity = buildingRepository.save(buildingEntity);
		return buildingConverter.convertToDTO(buildingEntity);
	}

	@Override
	public ListBuildingAndCount findAll(BuildingSearchBuilder builder, Pageable pageable) {
		ListBuildingAndCount rs = buildingRepository.findAll(builder, pageable);
		rs.setBuildingDtos(rs.getBuildingEntities().stream().map(item -> buildingConverter.convertToDTO(item)).collect(Collectors.toList()));
		rs.setBuildingEntities(null);
		return rs;
	}

	@Override
	public int count(BuildingSearchBuilder builder) {
		return buildingRepository.count(builder).intValue();
	}

	@Override
	public BuildingDTO findById(Long id) {
		BuildingEntity buildingEntity = buildingRepository.findById(id).get();
		return buildingConverter.convertToDTO(buildingEntity);
	}

	@Override
	public void deleteBuilding(Long[] ids) {
		for(Long id : ids) {
			rentAreaRepository.deleteByBuildingId(id);
			BuildingEntity buildingEntity = buildingRepository.findById(id).get();
			for (UserEntity userEntity : buildingEntity.getStaffs()) {
				userEntity.getBuildings().remove(buildingEntity);
			}
			buildingRepository.deleteById(id);
		}
	}

	@Override
	public void handoverBuilding(AssignmentBuildingInput ass) {
		Long buildingId = ass.getBuildingId();
		buildingRepository.deleteAss(buildingId);
		for (Long id : ass.getUserIds()) {
			buildingRepository.handoverBuilding(buildingId, id);
		}
	}
}
