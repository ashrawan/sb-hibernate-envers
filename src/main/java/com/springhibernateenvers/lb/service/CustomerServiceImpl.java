package com.springhibernateenvers.lb.service;

import com.springhibernateenvers.lb.dto.CustomerDTO;
import com.springhibernateenvers.lb.entity.Asset;
import com.springhibernateenvers.lb.entity.Customer;
import com.springhibernateenvers.lb.repository.CustomerRepository;
import com.springhibernateenvers.lb.utils.exceptions.DataNotFoundException;
import com.springhibernateenvers.lb.utils.mapper.CustomerMapper;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager em;

    public List<Asset> getAssetRevisionByAssetId(Long id) {

        List<Asset> revisionsAssets = AuditReaderFactory.get(em)
                .createQuery()
                .forRevisionsOfEntity(Asset.class, true, true)
                .add(AuditEntity.id().eq(id))
                .getResultList();

        return revisionsAssets;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> CustomerMapper.mapToDTOWithRelation(customer))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Customer Not Found"));
        return CustomerMapper.mapToDTOWithRelation(customer);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(CustomerMapper.mapToEntity(customerDTO));
        return CustomerMapper.mapToDTOWithRelation(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer returnedCustomer = customerRepository.findById(customerDTO.getCustomerId())
                .orElseThrow(() -> new DataNotFoundException("Customer Not Found"));
        returnedCustomer.setFullName(customerDTO.getFullName());
        returnedCustomer.setBalAmount(customerDTO.getBalAmount());
        return CustomerMapper.mapToDTOWithRelation(customerRepository.save(returnedCustomer));
    }

    @Override
    public boolean deleteCustomer(Long id) {
        Customer returnedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Customer Not Found"));
        customerRepository.delete(returnedCustomer);
        return true;
    }

}
