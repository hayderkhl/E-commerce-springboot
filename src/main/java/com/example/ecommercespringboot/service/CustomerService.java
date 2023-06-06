package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.CustomerDto;
import com.example.ecommercespringboot.models.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    CustomerDto signUp(CustomerDto dto);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<List<Customer>> getAllCustomer();
}
