package com.nkm.repository.custom.impl;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nkm.builder.BuildingSearchBuilder;
import com.nkm.entity.BuildingEntity;
import com.nkm.repository.custom.BuildingRepositoryCustome;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustome {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageable pageable) {
		try {
			Map<String, Object> properties = buildMapSearch(builder);
			StringBuilder sql = new StringBuilder("SELECT * FROM building AS A WHERE 1=1 ");
			StringBuilder whereClause = buildWhereClause(builder);
			sql = createSQLfindAll(sql, properties);
			sql.append(whereClause);
			Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
			if (pageable != null) {
				query.setFirstResult((int) pageable.getOffset());
				query.setMaxResults(pageable.getPageSize());
			}
			List<BuildingEntity> lstRs = query.getResultList();
			System.out.println(lstRs);
			return query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<BuildingEntity>();
	}

	private StringBuilder createSQLfindAll(StringBuilder rs, Map<String, Object> properties) {
		if (properties != null && properties.size() > 0) {
			String[] params = new String[properties.size()];
			Object[] values = new Object[properties.size()];
			int i = 0;
			for (Map.Entry<?, ?> item : properties.entrySet()) {
				params[i] = (String) item.getKey();
				values[i] = item.getValue();
				i++;
			}
			for (int j = 0; j < params.length; j++) {
				if (values[j] instanceof String) {
					if (StringUtils.isNotBlank(values[j].toString())) {
						rs.append(" AND LOWER(" + params[j] + ") LIKE LOWER('%" + values[j] + "%') ");
					}
				} else if (values[j] instanceof Integer || values[j] instanceof Long) {
					rs.append(" AND " + params[j] + " = " + values[j] + " ");
				}
			}
		}
		return rs;
	}

	private Map<String, Object> buildMapSearch(BuildingSearchBuilder builder) {
		Map<String, Object> result = new HashMap<>();
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				if (!field.getName().equals("buildingTypes") && !field.getName().startsWith("costRen")
						&& !field.getName().startsWith("areaRent")) {
					field.setAccessible(true);
					if (field.get(builder) != null) {
						if (field.getName().equals("numberOfBasement") || field.getName().equals("buildingArea")) {
							if (StringUtils.isNotBlank((String) field.get(builder))) {
								result.put(field.getName().toLowerCase(),
										Integer.parseInt(((String) field.get(builder))));
							}
						} else {
							result.put(field.getName().toLowerCase(), field.get(builder));
						}
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	private StringBuilder buildWhereClause(BuildingSearchBuilder builder) {
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(" AND A.status = 1 ");
		if (StringUtils.isNotBlank(builder.getCostRentFrom())) {
			whereClause.append(" AND costrent >= " + builder.getCostRentFrom() + " ");
		}
		if (StringUtils.isNotBlank(builder.getCostRentTo())) {
			whereClause.append(" AND costrent <= " + builder.getCostRentTo() + " ");
		}
		if (StringUtils.isNotBlank(builder.getAreaRentFrom()) || StringUtils.isNotBlank(builder.getAreaRentTo())) {
			whereClause.append(" AND EXISTS(SELECT * FROM rentarea ra WHERE (ra.buildingid = A.id ");
			if (StringUtils.isNotBlank(builder.getAreaRentFrom())) {
				whereClause.append(" AND ra.value >= '" + builder.getAreaRentFrom() + "' ");
			}
			if (StringUtils.isNotBlank(builder.getAreaRentTo())) {
				whereClause.append(" AND ra.value <= '" + builder.getAreaRentTo() + "' ");
			}
			whereClause.append(" )) ");
		}
		if (builder.getBuildingTypes().length > 0) {
			whereClause.append(" AND ( A.type LIKE '%" + builder.getBuildingTypes()[0] + "%'");
			Arrays.stream(builder.getBuildingTypes()).filter(item -> !item.equals(builder.getBuildingTypes()[0]))
					.forEach(item -> whereClause.append(" OR A.type LIKE '%" + item + "%' "));
			whereClause.append(" ) ");
		}
		return whereClause;
	}

	@Override
	public Long count(BuildingSearchBuilder builder) {
		try {
			Map<String, Object> properties = buildMapSearch(builder);
			StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM building AS A WHERE 1=1 ");
			StringBuilder whereClause = buildWhereClause(builder);
			sql = createSQLfindAll(sql, properties);
			sql.append(whereClause);
			Query query = entityManager.createNativeQuery(sql.toString());
			List<BigInteger> resultLst = query.getResultList();
			return Long.parseLong(resultLst.get(0).toString(), 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public void deleteBuilding(Long id) {
		try {
			StringBuilder sql = new StringBuilder("UPDATE building SET STATUS = 0 WHERE id = :id");
			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter("id", id);
			query.executeUpdate();
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
