package com.nkm.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUltil {
	@SuppressWarnings("unchecked")
	public static <T> T toModel(Class<T> zClass, HttpServletRequest req) {
		T object = null;
		try {
			object = zClass.newInstance();
			BeanUtils.populate(object, req.getParameterMap());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return object;

	}
}
