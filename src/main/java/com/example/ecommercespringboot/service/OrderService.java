package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.OrderDto;
//import com.example.ecommercespringboot.dto.OrderStatusHistoryDto;
import com.example.ecommercespringboot.models.Order;
import com.example.ecommercespringboot.models.Order_status_history;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    ResponseEntity<?> placeOrder(OrderDto orderDto);

    OrderDto getOrderByCustomerId(Integer customerId);

    ResponseEntity<?> changeOrderStatus(Integer orderstatusid, Integer orderid);

}
