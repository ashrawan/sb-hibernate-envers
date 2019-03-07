package com.springhibernateenvers.lb.utils.mapper;

import com.springhibernateenvers.lb.dto.CustomerDTO;
import com.springhibernateenvers.lb.entity.Customer;

import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getEmployeeId());
        customerDTO.setFullName(customer.getFullName());
        customerDTO.setBalAmount(customer.getBalAmount());
        return customerDTO;
    }

    public static CustomerDTO mapToDTOWithRelation(Customer customer) {
        CustomerDTO customerDTO = mapToDTO(customer);
        if (customer.getAssets() != null) {
            customerDTO.setAssetDTOSet(customer.getAssets().stream()
                    .map(asset -> AssetMapper.mapToDTO(asset))
                    .collect(Collectors.toSet()));
        }
        return customerDTO;
    }

    public static Customer mapToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setEmployeeId(customerDTO.getCustomerId());
        customer.setFullName(customerDTO.getFullName());
        customer.setBalAmount(customerDTO.getBalAmount());

        return customer;
    }

    public static Customer mapToEntityWithRelation(CustomerDTO customerDTO) {
        Customer customer = mapToEntity(customerDTO);
        if (customerDTO.getAssetDTOSet() != null) {
            customer.setAssets(customerDTO.getAssetDTOSet().stream()
                    .map(asset -> AssetMapper.mapToEntity(asset))
                    .collect(Collectors.toSet()));
        }
        return customer;
    }
}
