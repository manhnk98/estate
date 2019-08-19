package com.nkm.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	private String value;

	public HttpUtil(String value) {
		this.value = value;
	}

	public <T> T toModel(Class<T> zClass) { // map value field trong JSON String với field trong DTO
		try {
			return new ObjectMapper().readValue(value, zClass);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	public static HttpUtil of(BufferedReader reader) { // dùng để convert dữ liệu từ JSON -> String JSON
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString());
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
		return new HttpUtil(sb.toString());
	}
}
