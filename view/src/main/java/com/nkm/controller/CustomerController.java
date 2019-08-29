package com.nkm.controller;

import com.nkm.builder.CustomerSearchBuilder;
import com.nkm.dto.AssignmentCustomerDTO;
import com.nkm.dto.CustomerDTO;
import com.nkm.dto.UserDTO;
import com.nkm.service.ICustomerService;
import com.nkm.service.IUserService;
import com.nkm.utils.FormUltil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-customer"})
public class CustomerController extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    final static Logger logger = Logger.getLogger(CustomerController.class);

    @Inject
    private ICustomerService customerService;

    @Inject
    private IUserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        CustomerDTO model = FormUltil.toModel(CustomerDTO.class, request);
        String action = request.getParameter("action");
        String url = "";
        if(action.equals("LIST")) {
            url = "/views/customer/list.jsp";
            CustomerSearchBuilder builder = initCustomerBuilder(model);
            StringBuilder findAllAPI = initCustomerParams(model, builder, "http://localhost:8087/api/customer");
            StringBuilder getTotalItemAPI = initCustomerParams(model, builder, "http://localhost:8087/api/customer/totalItem");
            model.setTotalItem(customerService.getTotalItem(getTotalItemAPI.toString()));
            model.setTotalPage((int) Math.ceil((double) model.getTotalItem()/model.getMaxPageItem()));
            model.setListResults(customerService.findAll(findAllAPI.toString()));

            StringBuilder findAllStaff = new StringBuilder("http://localhost:8087/api/user?role=STAFF");
            List<UserDTO> staffs = userService.findAll(findAllStaff.toString());
            request.setAttribute("staffs", staffs);

        } else if(action.equals("EDIT")) {
            url = "/views/customer/edit.jsp";
            if (model.getId() != null) {
                String URLfindById = "http://localhost:8087/api/"+model.getId()+"/customer";
                model = customerService.findById(URLfindById);
                String URLfindByCSKH = "http://localhost:8087/api/customer/"+model.getId();
                List<AssignmentCustomerDTO> assCustomer = new ArrayList<>();
                assCustomer = customerService.findAllByKey(URLfindByCSKH);
                request.setAttribute("assCustomer", assCustomer);
            }
        }
        request.setAttribute("model", model);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private StringBuilder initCustomerParams(CustomerDTO model, CustomerSearchBuilder builder, String s) {
        StringBuilder rs = new StringBuilder();
        rs.append(s);
        rs.append("?page="+model.getPage()+"&maxPageItem="+model.getMaxPageItem()+"");
        logger.info("begin add parameter to url API");
        Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(builder) != null){
                    rs.append("&"+field.getName()+"="+field.get(builder)+"");
                }
            } catch (IllegalAccessException e) {
                logger.error("ERROR add parameter to URL API : "+e.getMessage(), e);
            }
        }
        if (StringUtils.isNotBlank(model.getSortName())){
            rs.append("&sortBy="+model.getSortBy()+"&sortName="+model.getSortName()+"");
        }
        logger.info("URL API : "+rs.toString());
        return rs;
    }

    private CustomerSearchBuilder initCustomerBuilder(CustomerDTO model) {
        CustomerSearchBuilder builder = new CustomerSearchBuilder.Builder()
                .setFullName(model.getFullName())
                .setEmail(model.getEmail())
                .setPhoneNumber(model.getPhoneNumber())
                .setIdStaff(model.getIdStaff())
                .build();
        return builder;
    }

}
