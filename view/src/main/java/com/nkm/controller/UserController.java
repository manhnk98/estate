package com.nkm.controller;

import com.nkm.builder.BuildingSearchBuilder;
import com.nkm.dto.BuildingDTO;
import com.nkm.dto.UserDTO;
import com.nkm.service.IBuildingService;
import com.nkm.service.IUserService;
import com.nkm.service.impl.BuildingService;
import com.nkm.service.impl.UserService;
import com.nkm.utils.DataUltil;
import com.nkm.utils.FormUltil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

@WebServlet(urlPatterns = {"/employee-list"})
public class UserController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IUserService userService = new UserService();

	final static Logger logger = Logger.getLogger(UserController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		// load user for assignmentBuilding
		String ass = request.getParameter("assignment");
		if (ass.equals("BUILDING")){
			UserDTO users = new UserDTO();
			String findAll = "http://localhost:8087/api/user?role=STAFF&buildingId=" + idStr;
			users.setListResults(userService.findAll(findAll));
			String url = "/views/user/employeeList.jsp";
			request.setAttribute("users", users);
			request.setAttribute("buildingId", idStr);
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if (ass.equals("CUSTOMER")){
			UserDTO users = new UserDTO();
			String findAll = "http://localhost:8087/api/user?role=STAFF&customerId=" + idStr;
			users.setListResults(userService.findAll(findAll));
			String url = "/views/customer/staffList.jsp";
			request.setAttribute("users", users);
			request.setAttribute("customerId", idStr);
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}

	}
}
