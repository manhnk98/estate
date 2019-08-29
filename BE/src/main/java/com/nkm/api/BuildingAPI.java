package com.nkm.api;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nkm.api.input.building.AssignmentBuildingInput;
import com.nkm.api.output.building.ListBuildingAndCount;
import com.nkm.api.output.building.TotalItem;
import com.nkm.builder.BuildingSearchBuilder;
import com.nkm.dto.BuildingDTO;
import com.nkm.service.IBuildingService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BuildingAPI {
	
	@Autowired
	private IBuildingService buildingService;
	
	@PostMapping(value = {"/api/building"})
	public BuildingDTO saveBuilding(@RequestBody BuildingDTO buildingDTO) {
		return buildingService.save(buildingDTO);
	}
	
	@PostMapping(value = {"/api/building/assignment"})
	public void handoverBuilding(@RequestBody AssignmentBuildingInput ass) {
		buildingService.handoverBuilding(ass);
	}
	
	@GetMapping(value = {"/api/building"})
	public ListBuildingAndCount findAll(@RequestParam Map<String, Object> buildingQuery) {
		BuildingSearchBuilder builder = initBuildingBuilder(buildingQuery);
		Integer page = Integer.parseInt((String) buildingQuery.get("page"));
		Integer maxPageItem = Integer.parseInt((String) buildingQuery.get("maxPageItem"));
		Pageable pg = PageRequest.of(page-1, maxPageItem);
		return buildingService.findAll(builder, pg);
	}
	
	@GetMapping(value = {"/api/building/total"})
	public TotalItem getTotalItem(@RequestParam Map<String, Object> buildingQuery) {
		BuildingSearchBuilder builder = initBuildingBuilder(buildingQuery);
		return new TotalItem(buildingService.count(builder));
	}
	
	@GetMapping(value = {"/api/{id}/building"})
	public BuildingDTO findById(@PathVariable("id") Long id) {
		return buildingService.findById(id);
	}
	
	@PutMapping(value = {"/api/building"})
	public void updateBuilding(@RequestBody BuildingDTO buildingDTO) {
		buildingService.save(buildingDTO);
	}
	
	@DeleteMapping(value = {"/api/building"})
	public void deleteBuilding(@RequestBody Long[] ids) {
		buildingService.deleteBuilding(ids);
	}
	
	private BuildingSearchBuilder initBuildingBuilder(Map<String, Object> buildingQuery) {
		String[] buildingTypes = new String[]{};
		if(StringUtils.isNotBlank((String) buildingQuery.get("buildingTypes"))) {
			buildingTypes = ((String) buildingQuery.get("buildingTypes")).split(",");
		}
		BuildingSearchBuilder buider = new BuildingSearchBuilder.Builder()
				.setName((String) buildingQuery.get("name"))
				.setBuildingArea((String) buildingQuery.get("buildingArea"))
				.setDistrict((String) buildingQuery.get("district"))
				.setWard((String) buildingQuery.get("ward"))
				.setStreet((String) buildingQuery.get("street"))
				.setNumberOfBasement((String) buildingQuery.get("numberOfBasement"))
				.setDirection((String) buildingQuery.get("direction"))
				.setLevel((String) buildingQuery.get("level"))
				.setAreaRentFrom((String) buildingQuery.get("areaRentFrom"))
				.setAreaRentTo((String) buildingQuery.get("areaRentTo"))
				.setCostRentFrom((String) buildingQuery.get("costRentFrom"))
				.setCostRentTo((String) buildingQuery.get("costRentTo"))
				.setManagerName((String) buildingQuery.get("managerName"))
				.setManagerPhone((String) buildingQuery.get("managerPhone"))
				.setBuildingTypes(buildingTypes).build();
		return buider;
	}
}
