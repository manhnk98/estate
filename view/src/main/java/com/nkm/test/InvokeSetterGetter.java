package com.nkm.test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.nkm.entity.BuildingEntity;

public class InvokeSetterGetter {
	public static void main(String[] args) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setName("nkm");
		buildingEntity.setNumberOfBasement(1);
		buildingEntity.setBuildingArea(1);
		List<String> lst = new ArrayList<>();
		lst.add("name");
		lst.add("numberOfBasement");
		lst.add("buildingArea");
		InvokeSetterGetter objInvokeSetterGetter = new InvokeSetterGetter();
		objInvokeSetterGetter.invokeGetter(buildingEntity, lst);
	}

	private void invokeGetter(Object obj, List<String> variableName) {
		for(String str : variableName) {
			try {
				PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(str, obj.getClass());
				Object variableValue = objPropertyDescriptor.getReadMethod().invoke(obj);
				System.out.println(variableValue);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| IntrospectionException e) {
				e.printStackTrace();
			}
		}
	}
}