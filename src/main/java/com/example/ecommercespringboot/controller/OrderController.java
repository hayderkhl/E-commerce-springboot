package com.example.ecommercespringboot.controller;

import com.example.ecommercespringboot.dto.CartDto;
import com.example.ecommercespringboot.dto.OrderDto;
import com.example.ecommercespringboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place/new/order")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDto dto)
    {
        return orderService.placeOrder(dto);
    }

    @GetMapping("/get/customer/order/{id}")
    public OrderDto getCustomerById(@PathVariable Integer id)
    {
        return orderService.getOrderByCustomerId(id);
    }


}
