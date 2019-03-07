package com.springhibernateenvers.lb.controller;

import com.springhibernateenvers.lb.dto.CustomerDTO;
import com.springhibernateenvers.lb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/revisions/{id}")
    public ResponseEntity<?> getAssetRevisionByAssetId(@PathVariable long id) {

        return new ResponseEntity<>(customerService.getAssetRevisionByAssetId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {

        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable long id) {

        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {

        return new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO) {

        return new ResponseEntity<>(customerService.updateCustomer(customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long id) {

        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }
}
