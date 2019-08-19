package com.nkm.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkm.dto.BuildingDTO;
import com.nkm.service.IBuildingService;
import com.nkm.service.impl.BuildingService;
import com.nkm.utils.HttpUtil;

@WebServlet(urlPatterns = { "/api-admin-building"})
public class BuildingAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@Inject
	private IBuildingService service = new BuildingService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); // trả về dữ liệu JSON cho client
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		buildingDTO = service.save(buildingDTO);
		mapper.writeValue(response.getOutputStream(), buildingDTO);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); // trả về dữ liệu JSON cho client
		BuildingDTO updateBuilding = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		service.update(updateBuilding, updateBuilding.getId());
		mapper.writeValue(response.getOutputStream(), "");
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); // trả về dữ liệu JSON cho client
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		service.delete(buildingDTO.getIds());
		mapper.writeValue(response.getOutputStream(), "");
	}
}
