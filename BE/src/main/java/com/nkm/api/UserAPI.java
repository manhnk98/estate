package com.nkm.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nkm.builder.UserSearchBuilder;
import com.nkm.dto.UserDTO;
import com.nkm.service.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserAPI {

	@Autowired
	private IUserService userService;

	@GetMapping(value = { "/api/user" })
	public List<UserDTO> findAllStaff(@RequestParam Map<String, Object> userQuery) {
		UserSearchBuilder userSearchBuilder = initUserSearchBuilder(userQuery);
		List<UserDTO> rs = userService.getAllStaff(userSearchBuilder);
		return rs;
	}

	private UserSearchBuilder initUserSearchBuilder(Map<String, Object> userQuery) {
		UserSearchBuilder builder = new UserSearchBuilder.Builder()
				.setName((String) userQuery.get("name"))
				.setRole((String) userQuery.get("role"))
				.setBuildingId((String) userQuery.get("buildingId"))
				.setCustomerId((String) userQuery.get("customerId"))
				.build();
		return builder;
	}
}
