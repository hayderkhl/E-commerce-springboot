package com.example.ecommercespringboot.controller;

import com.example.ecommercespringboot.dto.OrderStatusDto;
import com.example.ecommercespringboot.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/status")
public class OrderStatusController {

    @Autowired
    OrderStatusService orderStatusService;

    @PostMapping("/create")
    public OrderStatusDto create(@RequestBody OrderStatusDto dto)
    {
        return orderStatusService.create(dto);
    }
}
