package com.nkm.repository.impl;

import com.nkm.entity.RentAreaEntity;
import com.nkm.repository.IRentAreaRepository;

public class RentAreaRepository extends AbstractJDBC<RentAreaEntity> implements IRentAreaRepository {

	@Override
	public void deleteByBuildingId(Long id) {
		String where = " buildingid = " + id;
		this.deleteByProperty(where);
	}
}
