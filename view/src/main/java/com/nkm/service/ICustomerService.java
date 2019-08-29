package com.nkm.service;

import com.nkm.api.ListCustomerAndCount;
import com.nkm.dto.AssignmentCustomerDTO;
import com.nkm.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {
    Long getTotalItem(String url);
    List<CustomerDTO> findAll(String url);
    void update(String urlNewBuilding);
    CustomerDTO findById(String url);
    List<AssignmentCustomerDTO> findAllByKey(String urLfindByKey);
    ListCustomerAndCount findAllCustomer(String url);
}
