package com.nkm.repository;

import java.util.List;
import java.util.Map;

import com.nkm.builder.BuildingSearchBuilder;
import com.nkm.paging.Pageble;

public interface JpaRepository<T> {
	Long insert(Object object);
	void update(Object object);
	void delete(Long id);
	void deleteByProperty(String where);
	@SuppressWarnings("hiding")
	<T> T findById(Long id);
	List<T> findAllAbs(Map<String, Object> properties, Pageble pageble, Object...objects);
	Long countByProperties(Map<String, Object> properties, Object...objects);
	Long countByProperties(BuildingSearchBuilder builder);
}
