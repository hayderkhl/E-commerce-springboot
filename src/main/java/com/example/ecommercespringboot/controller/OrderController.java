package com.example.ecommercespringboot.controller;

import com.example.ecommercespringboot.dto.CartDto;
import com.example.ecommercespringboot.dto.OrderDto;
import com.example.ecommercespringboot.dto.OrderStatusDto;
//import com.example.ecommercespringboot.dto.OrderStatusHistoryDto;
import com.example.ecommercespringboot.models.Order;
import com.example.ecommercespringboot.models.OrderStatus;
import com.example.ecommercespringboot.models.Order_status_history;
import com.example.ecommercespringboot.repository.OrderRepository;
import com.example.ecommercespringboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

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


    @PostMapping("/update/order/status/{orderStatusid}/{orderid}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Integer orderStatusid, @PathVariable Integer orderid)
    {
        return orderService.changeOrderStatus(orderStatusid, orderid);
    }


}
