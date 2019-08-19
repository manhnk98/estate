package com.nkm.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkm.dto.UserDTO;
import com.nkm.utils.HttpUtil;

@WebServlet(urlPatterns = {"/api-admin-user"})
public class UserAPI extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); // trả về dữ liệu JSON cho client
		UserDTO userDTO = HttpUtil.of(request.getReader()).toModel(UserDTO.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), userDTO);
	}
	
}
