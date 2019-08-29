package com.nkm.service;

import java.util.List;

import com.nkm.api.ListBuildingAndCount;
import com.nkm.builder.BuildingSearchBuilder;
import com.nkm.dto.BuildingDTO;
import com.nkm.paging.Pageble;

public interface IBuildingService {
	BuildingDTO save(BuildingDTO newBuilding);
	void update(BuildingDTO updateBuilding, Long id);
	void update(String urlNewBuilding);
	List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageble pageble);
	List<BuildingDTO> findAll(String url);
	Long getTotalItem(BuildingSearchBuilder builder);
	Long getTotalItem(String url);
	BuildingDTO findById(Long id);
	void delete(Long[] ids);
	BuildingDTO findById(String url);

    ListBuildingAndCount findAllBuilding(String url);
}
