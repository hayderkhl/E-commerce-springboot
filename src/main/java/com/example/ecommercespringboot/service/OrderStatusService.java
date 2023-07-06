package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.OrderStatusDto;
import org.springframework.http.ResponseEntity;

public interface OrderStatusService {

    OrderStatusDto create(OrderStatusDto dto);

}
