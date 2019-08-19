package com.nkm.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TestMain {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String api = "http://localhost:8087/api/building/total?name=9 sao";
		String rs = URLEncoder.encode(api, StandardCharsets.UTF_8.toString());
		System.out.println("rs : "+api+rs);
	}
}
