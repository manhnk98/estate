package com.nkm.repository;

import java.util.List;

import com.nkm.builder.BuildingSearchBuilder;
import com.nkm.entity.BuildingEntity;
import com.nkm.paging.Pageble;

public interface IBuildingRepository extends JpaRepository<BuildingEntity>{
	List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageble pageble);
	Long countBuildings(BuildingSearchBuilder builder);
}
