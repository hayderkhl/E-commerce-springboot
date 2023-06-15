package com.example.ecommercespringboot.controller;

import com.example.ecommercespringboot.dto.CustomerDto;
import com.example.ecommercespringboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
   private CustomerService customerService;

    @PostMapping("/user/signup")
    public CustomerDto signUp(
            @RequestBody CustomerDto customerDto
    ) {
        return customerService.signUp(customerDto);
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMap) {
        try {
            return customerService.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed: Invalid credentials");

    }
}
