package com.nkm.repository;

import com.nkm.entity.RentAreaEntity;

public interface IRentAreaRepository extends JpaRepository<RentAreaEntity>{
	void deleteByBuildingId(Long id);
}
