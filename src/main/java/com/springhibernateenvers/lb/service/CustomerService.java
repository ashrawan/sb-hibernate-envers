package com.springhibernateenvers.lb.service;

import com.springhibernateenvers.lb.dto.CustomerDTO;
import com.springhibernateenvers.lb.entity.Asset;
import com.springhibernateenvers.lb.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Asset> getAssetRevisionByAssetId(Long id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    boolean deleteCustomer(Long id);
}
