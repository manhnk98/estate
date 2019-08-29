package com.nkm.api;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nkm.api.input.building.AssignmentCustomerInput;
import com.nkm.api.output.building.TotalItem;
import com.nkm.builder.CustomerSearchBuilder;
import com.nkm.dto.AssignmentCustomerDTO;
import com.nkm.dto.CustomerDTO;
import com.nkm.service.ICustomerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CustomerAPI {

	@Autowired
	private ICustomerService customerService;

	@GetMapping(value = { "/api/customer" })
	public List<CustomerDTO> findAll(@RequestParam Map<String, Object> customerQuery) {
		CustomerSearchBuilder builder = initCustomerBuilder(customerQuery);
		Integer page = Integer.parseInt((String) customerQuery.get("page"));
		Integer maxPageItem = Integer.parseInt((String) customerQuery.get("maxPageItem"));
		Pageable pageable = PageRequest.of(page - 1, maxPageItem);
		
		String idStaff = (String) customerQuery.get("idStaff");
		Long staffId = StringUtils.isNotBlank(idStaff) ? Long.parseLong(idStaff) : null;
		System.out.println(staffId);
		return customerService.findAll(builder, pageable, staffId);
	}

	@GetMapping(value = { "/api/{id}/customer" })
	public CustomerDTO findById(@PathVariable("id") Long id) {
		return customerService.findById(id);
	}

	@GetMapping(value = { "/api/customer/{id}" })
	public List<AssignmentCustomerDTO> findByKey(@PathVariable("id") Long id) {
		return customerService.findAllByKeyAndId(id);
	}

	@GetMapping(value = { "/api/customer/totalItem" })
	public TotalItem getTotalItem(@RequestParam Map<String, Object> customerQuery) {
		CustomerSearchBuilder builder = initCustomerBuilder(customerQuery);
		return new TotalItem(customerService.count(builder));
	}

	@PostMapping(value = { "/api/customer" })
	public CustomerDTO insert(@RequestBody CustomerDTO dto) {
		return customerService.save(dto);
	}

	@PutMapping(value = { "/api/customer" })
	public CustomerDTO updateCustomer(@RequestBody CustomerDTO dto) {
		return customerService.save(dto);
	}

	@PostMapping(value = { "/api/customer/assignment" })
	public void handoverCustomer(@RequestBody AssignmentCustomerInput ass) {
		customerService.handoverBuilding(ass);
	}

	@PutMapping(value = { "/api/customer/deal" })
	public void activityUpdate(@RequestBody AssignmentCustomerDTO dto) {
		customerService.activityUpdate(dto);
	}

	private CustomerSearchBuilder initCustomerBuilder(Map<String, Object> customerQuery) {
		CustomerSearchBuilder builder = new CustomerSearchBuilder.Builder()
				.setFullName((String) customerQuery.get("fullName")).setEmail((String) customerQuery.get("email"))
				.setPhoneNumber((String) customerQuery.get("phoneNumber")).build();
		return builder;
	}
}
