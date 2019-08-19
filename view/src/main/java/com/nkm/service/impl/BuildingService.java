package com.nkm.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkm.controller.BuildingController;
import com.nkm.utils.HttpClientUtils;
import org.apache.commons.lang.StringUtils;

import com.nkm.builder.BuildingSearchBuilder;
import com.nkm.constant.SystemConstant;
import com.nkm.converter.BuildingConverter;
import com.nkm.dto.BuildingDTO;
import com.nkm.entity.BuildingEntity;
import com.nkm.entity.RentAreaEntity;
import com.nkm.paging.Pageble;
import com.nkm.repository.IBuildingRepository;
import com.nkm.repository.IRentAreaRepository;
import com.nkm.repository.impl.BuildingRepository;
import com.nkm.repository.impl.RentAreaRepository;
import com.nkm.service.IBuildingService;
import org.apache.log4j.Logger;

public class BuildingService implements IBuildingService {

	public static BuildingService getInstance() {
		return new BuildingService();
	}
	
//	@Inject
	private IBuildingRepository buildingRepository = new BuildingRepository();
	
//	@Inject
	private IRentAreaRepository rentAreaRepository = new RentAreaRepository();
	
//	@Inject
	private BuildingConverter buildingConverter = new BuildingConverter();

	final static Logger logger = Logger.getLogger(BuildingService.class);


	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		buildingEntity.setCreatedBy("");
		buildingEntity.setType(StringUtils.join(buildingDTO.getBuildingTypes(), ","));
		buildingEntity.setStatus(SystemConstant.STATUS_INSERT_DEFAULT);
		Long id =  buildingRepository.insert(buildingEntity);
		
		// save renArea
//		System.out.println("id = "+id);
		RentAreaEntity renArea = new RentAreaEntity();
		for(String item : buildingDTO.getRentArea().split(",")) {
			renArea.setValue(item);
			renArea.setBuildingId(id);
			rentAreaRepository.insert(renArea);
		}
		
		return buildingConverter.convertToDTO(buildingRepository.findById(id));
	}

	@Override
	public BuildingDTO findById(Long id) {
		return buildingConverter.convertToDTO(buildingRepository.findById(id));
	}

	@Override
	public List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageble pageble) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder, pageble);  //buildingRepository.findAll(builder, pageble);
		// java 7
		List<BuildingDTO> buildingDTOs = new ArrayList<>();
		BuildingDTO dto = new BuildingDTO();
		for(BuildingEntity entity : buildingEntities) {
			dto = buildingConverter.convertToDTO(entity);
			buildingDTOs.add(dto);
		}
		
		// java 8
//		List<BuildingDTO> buildingDTOs = buildingEntities.stream().map(item -> buildingConverter.convertToDTO(item)).collect(Collectors.toList());
		return buildingDTOs;
	}

	@Override
	public List<BuildingDTO> findAll(String url) {
		String result = HttpClientUtils.httpGet(url);
		try {
			List<BuildingDTO> rs = Arrays.asList(new ObjectMapper().readValue(result, BuildingDTO[].class));
			return rs;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<>();
	}

	@Override
	public Long getTotalItem(BuildingSearchBuilder builder) {
		return buildingRepository.countByProperties(builder);
	}

	@Override
	public Long getTotalItem(String url) {
		String result = HttpClientUtils.httpGet(url);
		try {
			BuildingDTO buildingDTO = new ObjectMapper().readValue(result, BuildingDTO.class);
			return buildingDTO.getTotalItem();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return 0L;
	}

	@Override
	public void update(BuildingDTO updateBuilding, Long id) {
		BuildingEntity oldBuilding = buildingRepository.findById(id);
		BuildingEntity newBuilding = buildingConverter.convertToEntity(updateBuilding);
		newBuilding.setCreatedBy(oldBuilding.getCreatedBy());
		newBuilding.setCreatedDate(oldBuilding.getCreatedDate());
		newBuilding.setType(StringUtils.join(updateBuilding.getBuildingTypes(), ","));
		newBuilding.setStatus(1);
		updateRentArea(updateBuilding.getRentArea(), id);
		buildingRepository.update(newBuilding);
	}

	@Override
	public void update(String urlNewBuilding) {
		String result = HttpClientUtils.httpGet(urlNewBuilding);
		try {
			BuildingDTO newBuildingDTO = new ObjectMapper().readValue(result, BuildingDTO.class);

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void updateRentArea(String rentArea, Long buildingId) {
		// delete rent area by buildingid
		rentAreaRepository.deleteByBuildingId(buildingId);
		
		RentAreaEntity entity = new RentAreaEntity();
		for(String item : rentArea.split(",")) {
			entity.setValue(item);
			entity.setBuildingId(buildingId);
			rentAreaRepository.insert(entity);
		}
	}
	@Override
	public void delete(Long[] ids) {
		for(Long id : ids) {
			rentAreaRepository.deleteByBuildingId(id);
			buildingRepository.delete(id);
		}
	}

	@Override
	public BuildingDTO findById(String url) {
		String result = HttpClientUtils.httpGet(url);
		try {
			return new ObjectMapper().readValue(result, BuildingDTO.class);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return new BuildingDTO();
	}


}
