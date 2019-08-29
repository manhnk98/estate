package com.nkm.repository.custom.impl;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nkm.builder.CustomerSearchBuilder;
import com.nkm.entity.AssignmentCustomerEntity;
import com.nkm.entity.CustomerEntity;
import com.nkm.repository.custom.CustomerRepositoryCustome;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustome {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerEntity> findAll(CustomerSearchBuilder builder, Pageable pageable, Long staffId) {
		try {
			Map<String, Object> properties = buildMapSearch(builder);
			StringBuilder sql = new StringBuilder("SELECT * FROM customer as cus");
			if (staffId != null) {
				sql.append(" inner join assignmentcustomer as ass on ass.customer_id = cus.id inner join user on ass.user_id = user.id ");
				sql.append(" where user.status = 1 and user.id = :staffId and ass.type IS NULL ");
			} else {
				sql.append(" where 1=1 ");
			}
			sql = createSQLfindAll(sql, properties);
			Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
			if (staffId != null) {
				query.setParameter("staffId", staffId);
			}
			if (pageable != null) {
				query.setFirstResult((int) pageable.getOffset());
				query.setMaxResults(pageable.getPageSize());
			}
			List<CustomerEntity> lstRs = query.getResultList();
			return lstRs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CustomerEntity>();
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
						rs.append(" AND LOWER(" +"cus."+ params[j] + ") LIKE LOWER('%" + values[j] + "%') ");
					}
				} else if (values[j] instanceof Integer || values[j] instanceof Long) {
					rs.append(" AND " + params[j] + " = " + values[j] + " ");
				}
			}
		}
		return rs;
	}

	private Map<String, Object> buildMapSearch(CustomerSearchBuilder builder) {
		Map<String, Object> result = new HashMap<>();
		try {
			Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.get(builder) != null && StringUtils.isNotBlank(field.get(builder).toString())) {
					result.put(field.getName().toLowerCase(), field.get(builder));
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Long count(CustomerSearchBuilder builder) {
		try {
			Map<String, Object> properties = buildMapSearch(builder);
			StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM customer as cus WHERE 1=1 ");
			sql = createSQLfindAll(sql, properties);
			Query query = entityManager.createNativeQuery(sql.toString());
			List<BigInteger> resultLst = query.getResultList();
			return Long.parseLong(resultLst.get(0).toString(), 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public void deleteCustomer(Long id) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssignmentCustomerEntity> findAllByKeyAndId(Long id) {
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM assignmentcustomer WHERE 1=1 AND customer_id = :cusId");
			Query query = entityManager.createNativeQuery(sql.toString(), AssignmentCustomerEntity.class);
			query.setParameter("cusId", id);
			List<AssignmentCustomerEntity> lstRs = query.getResultList();
			return lstRs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<AssignmentCustomerEntity>();
	}

}
