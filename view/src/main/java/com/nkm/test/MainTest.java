package com.nkm.test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.nkm.annotation.Table;

public class MainTest<T> {

	public static void main(String[] args) {
//		System.out.println("getNameColumn : " + getNameColumn(BuildingEntity.class));
		
//		BuildingEntity buildingEntity = new BuildingEntity();
//		
//		buildingEntity.setName("nkm");
//		buildingEntity.setNumberOfBasement(1);
//		buildingEntity.setBuildingArea(1);
//		buildingEntity.setStreet("street 1");
//		buildingEntity.setCarCost("carcost 1");
//		
//		List<String> lst= getAllFieldInClass(BuildingEntity.class);
//		invokeGetter(buildingEntity, lst);
//		Object[] lstObj = invokeGetter(buildingEntity, lst);
//		for(Object x : lstObj) {
//			System.out.println(x);
//		}
		
	}
	
//	public static String getNameColumn(Class<?> zClass) { // add tên tất cả các cột trong Class (class super) vào 1 List<String>
//		StringBuilder stBuilder = new StringBuilder();
//		List<String> arrStr = new ArrayList<String>();
//		Field[] fields = zClass.getDeclaredFields();
//		addToArrString(fields, arrStr);
//		Class<?> parents = zClass.getSuperclass();
//		while (parents != null) {
//			Field[] parentsField = parents.getDeclaredFields();
//
//			for (Field field : parentsField) {
//				if (field.isAnnotationPresent(Column.class)) {
//					Column column = field.getAnnotation(Column.class);
//					if(!column.name().equals("id")) {
//						arrStr.add(column.name());
//					}
//				}
//			}
//			
//			addToArrString(parentsField, arrStr);
//			parents = parents.getSuperclass();
//		}
//		String arr[] = new String[countField(zClass)];
//		arrStr.toArray(arr);
//		stBuilder.append(arr[0]);
//		for (int i = 1; i < arr.length-1; i++) {
//			stBuilder.append(", " + arr[i]);
//		}
//		return stBuilder.toString();
//	}

//	private static void addToArrString(Field[] parentsField, List<String> arrStr) { // lưu tên cột vào mảng
//		for (Field field : parentsField) {
//			if (field.isAnnotationPresent(Column.class)) {
//				Column column = field.getAnnotation(Column.class);
//				arrStr.add(column.name());
//			}
//		}
//	}

//	public static int countField(Class<?> zClass) { // trả về số lượng các field trong Class
//		int count = 0;
//		Field[] fields = zClass.getDeclaredFields();
//		Class<?> parentsClass = zClass.getSuperclass();
//		while (parentsClass != null) {
//			Field[] parentsField = parentsClass.getDeclaredFields();
//			count += parentsField.length;
//			parentsClass = parentsClass.getSuperclass();
//		}
//		return (fields.length + count);
//	}

// =======================================================================================================

	public static String getTableName(Class<?> zClass) { // trả về tên Table trong Class
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			return table.name();
		}
		return null;
	}

//	public static String strValue(int count) { 
//		StringBuilder rs = new StringBuilder("");
//		rs.append("?");
//		for (int i = 1; i < count-1; i++) {
//			rs.append(", ?");
//		}
//		return rs.toString();
//	}
	
//	===================================================================================
	// Getter
	public static List<String> getAllFieldInClass(Class<?> zClass) { // lấy tất cả các field của class (super class)
		List<String> rs = new ArrayList<String>();
		Field[] fields = zClass.getDeclaredFields();
		for(Field field : fields) {
//			System.out.println(f.getName()+" ");
			rs.add(field.getName());
		}
		Class<?> parentClass = zClass.getSuperclass();
		while(parentClass != null) {
			Field[] parentFields = parentClass.getDeclaredFields();
			for(Field parentField : parentFields) {
				rs.add(parentField.getName());
			}
			parentClass = parentClass.getSuperclass();
		}
		return rs;
	}
	public static Object[] invokeGetter(Object obj, List<String> variableName) {
		ArrayList<Object> arrLst = new ArrayList<Object>();
		for(String str : variableName) {
			if(!str.equals("id")) {
				try {
					PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(str, obj.getClass());
					Object variableValue = objPropertyDescriptor.getReadMethod().invoke(obj);
					arrLst.add(variableValue);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IntrospectionException e) {
					e.printStackTrace();
				}
			}
		}
		return arrLst.toArray();
	}
}
