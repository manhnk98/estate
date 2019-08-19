package com.nkm.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nkm.builder.BuildingSearchBuilder;
import org.apache.commons.lang.StringUtils;

import com.nkm.annotation.Column;
import com.nkm.annotation.Table;
import com.nkm.mapper.ResultSetMapper;
import com.nkm.paging.Pageble;
import com.nkm.paging.Sorter;
import com.nkm.repository.JpaRepository;

public class AbstractJDBC<T> implements JpaRepository<T> {

	private Class<T> zClass;

	@SuppressWarnings("unchecked")
	public AbstractJDBC() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	private Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String databaseURL = "jdbc:mysql://localhost:3306/javaweb42019";
			String user = "root";
			String password = "admin";

			return DriverManager.getConnection(databaseURL, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Long insert(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			String sql = createSQLInsert();
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (conn != null) {
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();

				for (int i = 0; i < fields.length; i++) {
					int index = i + 1;
					Field field = fields[i];
					field.setAccessible(true);
					statement.setObject(index, field.get(object));
				}

				int indexParent = fields.length + 1;
				Class<?> parentClass = zClass.getSuperclass();
				while (parentClass != null) {
					for (int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i];
						field.setAccessible(true);
						statement.setObject(indexParent, field.get(object));
						indexParent = indexParent + 1;
					}
					parentClass = parentClass.getSuperclass();
				}
				int rowsInserted = statement.executeUpdate();
				resultSet = statement.getGeneratedKeys();
				conn.commit();
				System.out.println("sql : "+sql);
				if (rowsInserted > 0) {
					while (resultSet.next()) {
						return resultSet.getLong(1);
					}
				}
			}
		} catch (SQLException | IllegalAccessException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	private String createSQLInsert() {
		String tableName = "";
		StringBuilder fields = new StringBuilder();
		StringBuilder params = new StringBuilder();
		if (zClass.isAnnotationPresent(Table.class)) {
			tableName = zClass.getAnnotation(Table.class).name();
		}

		for (Field field : zClass.getDeclaredFields()) {
			if (fields.length() > 1) {
				fields.append(",");
				params.append(",");
			}
			if (field.isAnnotationPresent(Column.class)) {
				fields.append(field.getAnnotation(Column.class).name());
				params.append("?");
			}
		}

		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) {
				if (fields.length() > 1) {
					fields.append(",");
					params.append(",");
				}
				if (field.isAnnotationPresent(Column.class)) {
					fields.append(field.getAnnotation(Column.class).name());
					params.append("?");
				}
			}
			parentClass = parentClass.getSuperclass();
		}

		String sql = "INSERT INTO " + tableName + "(" + fields.toString() + ") VALUES(" + params.toString() + ")";
		return sql;
	}

	@Override
	public void update(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			String sql = createSQLUpdate();
			statement = conn.prepareStatement(sql);
			if (conn != null) {
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();

				for (int i = 0; i < fields.length; i++) {
					int index = i + 1;
					Field field = fields[i];
					field.setAccessible(true);
					statement.setObject(index, field.get(object));
				}

				int indexParent = fields.length + 1;
				Object id = null;
				Class<?> parentClass = zClass.getSuperclass();
				while (parentClass != null) {
					for (int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i];
						field.setAccessible(true);
						String name = field.getName();
						if (!name.equals("id")) {
							statement.setObject(indexParent, field.get(object));
							indexParent = indexParent + 1;
						} else {
							id = field.get(object);
						}
					}
					parentClass = parentClass.getSuperclass();
				}
				statement.setObject(indexParent, id);
				statement.executeUpdate();
				conn.commit();
			}
		} catch (SQLException | IllegalAccessException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private String createSQLUpdate() {
		String tableName = "";
		String where = null;
		StringBuilder sets = new StringBuilder();
		if (zClass.isAnnotationPresent(Table.class)) {
			tableName = zClass.getAnnotation(Table.class).name();
		}

		for (Field field : zClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class)) {
				String columnName = field.getAnnotation(Column.class).name();
				String value = columnName + " = ?";
				if (!columnName.equals("id")) {
					if (sets.length() > 1) {
						sets.append(", ");
					}
					sets.append(value);
				}
			}
		}

		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) {
				if (field.isAnnotationPresent(Column.class)) {
					String columnName = field.getAnnotation(Column.class).name();
					String value = columnName + " = ?";
					if (!columnName.equals("id")) {
						if (sets.length() > 1) {
							sets.append(", ");
						}
						sets.append(value);
					} else {
						where = " WHERE " + value;
					}
				}
			}
			parentClass = parentClass.getSuperclass();
		}

		String sql = "UPDATE " + tableName + " SET " + sets.toString() + where;
		return sql;
	}

	@Override
	public void delete(Long id) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String tableName = getTableName();
//			String sql = "DELETE FROM " + tableName + " WHERE id = ?";
			String sql = "UPDATE "+tableName+" A SET A.status = 0 WHERE A.id = "+id;
			statement = conn.prepareStatement(sql);
			System.out.println(sql);
//			statement.setObject(1, id);
			if (conn != null) {
				int rs = statement.executeUpdate();
				if(rs > 0) {
					conn.commit();
				}
			}
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private String getTableName() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			tableName = zClass.getAnnotation(Table.class).name();
		}
		return tableName;
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T findById(Long id) {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		String tableName = getTableName();
		String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
		try {
			conn = getConnection();
			statement = conn.prepareStatement(sql);
			statement.setObject(1, id);
			if (conn != null) {
				resultSet = statement.executeQuery();
				return resultSetMapper.mapRow(resultSet, zClass).get(0);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<T> findAllAbs(Map<String, Object> properties, Pageble pageRequest, Object...where) {
		Connection conn = null;
		ResultSet resultSet = null;
		Statement statement = null;
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		StringBuilder sql = createSQLfindAll(properties);
		if(where != null && where.length > 0) {
			sql.append(where[0]);
		}
		if (pageRequest != null) {
			if(pageRequest.getSorter()!= null && StringUtils.isNotBlank(pageRequest.getSorter().getSortName())) {
				Sorter sorter = pageRequest.getSorter();
				sql.append(" ORDER BY "+sorter.getSortName()+" "+sorter.getSortBy()+" ");
			}
			if (pageRequest.getOffset() != null && pageRequest.getLimit() != null) {
				sql.append(" LIMIT "+pageRequest.getOffset()+", "+pageRequest.getLimit()+" ");
			}
		}
		System.out.println(sql.toString());
		
		try {
			conn = getConnection();
			statement = conn.createStatement();
			if (conn != null) {
				resultSet = statement.executeQuery(sql.toString());
				return resultSetMapper.mapRow(resultSet, zClass);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return new ArrayList<T>();
	}

	private StringBuilder createSQLfindAll(Map<String, Object> properties) {
		String tableName = getTableName();
		StringBuilder rs = new StringBuilder("SELECT * FROM " + tableName + " A WHERE 1=1 ");
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
					if(StringUtils.isNotBlank(values[j].toString())) {
						rs.append(" AND LOWER(" + params[j] + ") LIKE LOWER('%" + values[j] + "%') ");
					}
				} else if (values[j] instanceof Integer || values[j] instanceof Long) {
					rs.append(" AND " + params[j] + " = " + values[j] + " ");
				}
			}
		}
		return rs;
	}

	@Override
	public void deleteByProperty(String where) {
		Connection conn = null;
		Statement statement = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String tableName = getTableName();
			String sql = "DELETE FROM " + tableName + " WHERE "+where;
			System.out.println("sql delete : "+sql);
			statement = conn.createStatement();
			if (conn != null) {
				statement.execute(sql);
				conn.commit();
			}
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Long countByProperties(Map<String, Object> properties, Object... where) {
		Connection conn = null;
		ResultSet resultSet = null;
		Statement statement = null;
		StringBuilder sql = createSQLCount(properties);
		if(where != null && where.length > 0) {
			sql.append(where[0]);
		}
		System.out.println("sql count : "+sql.toString());
		
		try {
			conn = getConnection();
			statement = conn.createStatement();
			if (conn != null) {
				resultSet = statement.executeQuery(sql.toString());
				while(resultSet.next()) {
					return resultSet.getLong("COUNT(A.ID)");
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return 0L;
	}

	@Override
	public Long countByProperties(BuildingSearchBuilder builder) {

		return null;
	}

	private StringBuilder createSQLCount(Map<String, Object> properties) {
		String tableName = getTableName();
		StringBuilder rs = new StringBuilder("SELECT COUNT(A.ID) FROM " + tableName + " A WHERE 1=1 ");
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
					if(StringUtils.isNotBlank(values[j].toString())) {
						rs.append(" AND LOWER(" + params[j] + ") LIKE LOWER('%" + values[j] + "%') ");
					}
				} else if (values[j] instanceof Integer || values[j] instanceof Long) {
					rs.append(" AND " + params[j] + " = " + values[j] + " ");
				}
			}
		}
		return rs;
	}
}
