package com.nkm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkm.dto.UserDTO;
import com.nkm.service.IUserService;
import com.nkm.service.impl.UserService;
import com.nkm.utils.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.nkm.builder.BuildingSearchBuilder;
import com.nkm.dto.BuildingDTO;
import com.nkm.service.IBuildingService;
import com.nkm.service.impl.BuildingService;
import com.nkm.utils.DataUltil;
import com.nkm.utils.FormUltil;

@WebServlet(urlPatterns = {"/admin-building"})
public class BuildingController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IBuildingService buildingService = new BuildingService();

	private IUserService userService = new UserService();

	final static Logger logger = Logger.getLogger(BuildingController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BuildingDTO model = FormUltil.toModel(BuildingDTO.class, request);
		System.out.println("model : "+model);
		String action = request.getParameter("action");
		String url = "";
		if(action.equals("LIST")) {
			url = "/views/building/list.jsp";
			BuildingSearchBuilder buider = initBuildingBuider(model);
			StringBuilder findAllAPI = initBuildingParams(model, buider, "http://localhost:8087/api/building");
			StringBuilder getTotalItemAPI = initBuildingParams(model, buider, "http://localhost:8087/api/building/total");
			model.setTotalItem(buildingService.getTotalItem(getTotalItemAPI.toString()));
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem()/model.getMaxPageItem()));
			model.setListResults(buildingService.findAll(findAllAPI.toString()));
		}else if(action.equals("EDIT")) {
			if (model.getId() != null) {
				String URLfindById = "http://localhost:8087/api/"+model.getId()+"/building";
				model = buildingService.findById(URLfindById);
			}
			url = "/views/building/edit.jsp";
		}
		request.setAttribute("districts", DataUltil.getDistricts());
		request.setAttribute("buildingtypes", DataUltil.getBuildingTypes());
		
		request.setAttribute("model", model);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private StringBuilder initBuildingParams(BuildingDTO model, BuildingSearchBuilder buider, String urlBe) {
		StringBuilder findAllAPI = new StringBuilder();
		findAllAPI.append(urlBe);
		findAllAPI.append("?page="+model.getPage()+"&maxPageItem="+model.getMaxPageItem()+"");
		logger.info("begin add parameter to url API");
		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (field.get(buider) != null){
					if (field.getName().equals("buildingTypes")){
						if (((String[]) field.get(buider)).length > 0){
							String[] buildingTypes = (String[]) field.get(buider);
							findAllAPI.append("&buildingTypes="+buildingTypes[0]+"");
							Arrays.stream(buildingTypes).filter(item -> !item.equals(buildingTypes[0]))
									.forEach(item -> findAllAPI.append(","+item+""));
						}
					} else {
						findAllAPI.append("&"+field.getName()+"="+field.get(buider)+"");
					}
				}
			} catch (IllegalAccessException e) {
				logger.error("ERROR add parameter to URL API : "+e.getMessage(), e);
			}
		}
		if (StringUtils.isNotBlank(model.getSortName())){
			findAllAPI.append("&sortBy="+model.getSortBy()+"&sortName="+model.getSortName()+"");
		}
		logger.info("URL API : "+findAllAPI.toString());
		return findAllAPI;
	}

	private BuildingSearchBuilder initBuildingBuider(BuildingDTO model) {
		BuildingSearchBuilder buider = new BuildingSearchBuilder.Builder()
				.setName(model.getName())
				.setBuildingArea(model.getBuildingArea())
				.setDistricts(model.getDistrict())
				.setWard(model.getWard())
				.setStreet(model.getStreet())
				.setNumberOfBasement(model.getNumberOfBasement())
				.setDirection(model.getDirection())
				.setLevel(model.getLevel())
				.setAreaRentFrom(model.getAreaRentFrom())
				.setAreaRentTo(model.getAreaRentTo())
				.setCostRentFrom(model.getCostRentFrom())
				.setCostRentTo(model.getCostRentTo())
				.setManagerName(model.getManagerName())
				.setManagerPhone(model.getManagerPhone())
				.setBuildingTypes(model.getBuildingTypes()).build();
		return buider;
	}
}
