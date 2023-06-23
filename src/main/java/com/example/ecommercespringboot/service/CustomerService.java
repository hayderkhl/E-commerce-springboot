package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.CustomerDto;
import com.example.ecommercespringboot.models.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerService {

    CustomerDto signUp(CustomerDto dto);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<String> logOut();

    ResponseEntity<List<Customer>> getAllCustomer();
    CustomerDto getCustomerByID(Integer id);
}
