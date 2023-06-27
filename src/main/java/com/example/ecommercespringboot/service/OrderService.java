package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.CartDto;
import com.example.ecommercespringboot.dto.OrderDto;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<?> placeOrder(OrderDto orderDto);

    OrderDto getOrderByCustomerId(Integer customerId);
}
