package com.nkm.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.nkm.annotation.Column;
import com.nkm.annotation.Entity;

public class ResultSetMapper<T> {
	@SuppressWarnings("unchecked")
	public List<T> mapRow(ResultSet rs, Class<?> zClass){
		List<T> results = new ArrayList<>();
		try {
			if(zClass.isAnnotationPresent(Entity.class)) {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				Field[] fields = zClass.getDeclaredFields();
				while(rs.next()) {
					T object = (T) zClass.newInstance();
					int getColumn = resultSetMetaData.getColumnCount();
					for(int i=0;i<getColumn;i++) {
					    String columnName = resultSetMetaData.getColumnName(i+1);
						Object columnValue = rs.getObject(i+1);
						convertToObject(fields,columnName,columnValue,object);
						Class<?> parentClass = zClass.getSuperclass();
						while(parentClass != null) {
							Field[] fieldParent = parentClass.getDeclaredFields();
							convertToObject(fieldParent,columnName,columnValue,object);
							parentClass = parentClass.getSuperclass();
						}
					}
					results.add(object);
				}
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void convertToObject(Field[] fields, String columnName, Object columnValue, T object) throws IllegalAccessException, InvocationTargetException {
		for(Field field : fields) {
			if(field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				if(column.name().equals(columnName) && columnValue != null) {
					BeanUtils.setProperty(object, field.getName(), columnValue);
					break;
				}
			}
		}
	}
}
