package com.nkm.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nkm")
public class TestAPI {
	@GetMapping("/api/test")
	public ResponseEntity<String> testSpringBoot() {
		return ResponseEntity.ok("Success");
	}
}
