package com.nkm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkm.dto.AssignmentCustomerDTO;
import com.nkm.dto.CustomerDTO;
import com.nkm.service.ICustomerService;
import com.nkm.utils.HttpClientUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerService implements ICustomerService {

    final static Logger logger = Logger.getLogger(CustomerService.class);

    @Override
    public Long getTotalItem(String url) {
        String result = HttpClientUtils.httpGet(url);
        try {
            CustomerDTO customerDTO = new ObjectMapper().readValue(result, CustomerDTO.class);
            return customerDTO.getTotalItem();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return 0L;
    }

    @Override
    public List<CustomerDTO> findAll(String url) {
        String result = HttpClientUtils.httpGet(url);
        try {
            List<CustomerDTO> rs = Arrays.asList(new ObjectMapper().readValue(result, CustomerDTO[].class));
            return rs;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    @Override
    public void update(String urlNewCustomer) {
        String result = HttpClientUtils.httpGet(urlNewCustomer);
        try {
            CustomerDTO customerDTO = new ObjectMapper().readValue(result, CustomerDTO.class);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public CustomerDTO findById(String url) {
        String result = HttpClientUtils.httpGet(url);
        try {
            return new ObjectMapper().readValue(result, CustomerDTO.class);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return new CustomerDTO();
    }

    @Override
    public List<AssignmentCustomerDTO> findAllByKey(String url) {
        String result = HttpClientUtils.httpGet(url);
        try {
            List<AssignmentCustomerDTO> rs = Arrays.asList(new ObjectMapper().readValue(result, AssignmentCustomerDTO[].class));
            return rs;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }
}
