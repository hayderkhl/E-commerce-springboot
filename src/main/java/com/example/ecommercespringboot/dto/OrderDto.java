package com.example.ecommercespringboot.dto;

import com.example.ecommercespringboot.models.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class OrderDto {

    private Integer id;
    private String code;
    private String address;
    @JsonIgnore
    private CartDto cart;
    private LocalDateTime orderDate;
    @JsonIgnore
    private CustomerDto customer;
    private Integer id_customer;
    private  Integer id_cart;
//    private String orderStatus;

    private OrderStatusDto orderStatusDto;
    private List<Order_productDTO> orderProducts;
    private List<OrderStatusHistoryDto> orderStatusHistory;


    public static OrderDto fromEntity(Order order)
    {
        if (order == null) {return null;}

        return OrderDto.builder()
                .id(order.getId())
                .code(order.getCode())
                .address(order.getAddress())
                .id_cart(order.getCart().getId())
                .id_customer(order.getCustomer().getId())
                .orderDate(order.getOrderDate())
                .orderStatusHistory(mapOrderStatus(order.getOrderStatusHistoryList()))
                //.orderStatus(order.getOrderStatus())
                .orderStatusDto(OrderStatusDto.fromEntity(order.getOrderStatus()))
                .customer(CustomerDto.fromEntity(order.getCustomer()))
                .cart(CartDto.fromEntity(order.getCart()))
                .orderProducts(mapOrderProducts(order.getOrderProducts()))
                .build();
    }


    public static Order toEntity(OrderDto orderDto)
    {
        if (orderDto == null) {return null;}

        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCode(orderDto.getCode());
        order.setCart(CartDto.toEntity(orderDto.getCart()));
        order.setCustomer(CustomerDto.toEntity(orderDto.getCustomer()));
        order.setOrderStatus(OrderStatusDto.toEntity(orderDto.orderStatusDto));
        order.setOrderDate(orderDto.getOrderDate());
        //order.setOrderStatus(orderDto.getOrderStatus());
        order.setAddress(orderDto.getAddress());

        return order;
    }

    private static List<Order_productDTO> mapOrderProducts(List<Order_product> orderProducts) {
        List<Order_productDTO> orderProductDTOS = new ArrayList<>();
        if (orderProducts != null) {
            for (Order_product orderProduct : orderProducts) {
                orderProductDTOS.add(Order_productDTO.fromEntity(orderProduct));
            }
        }
        return orderProductDTOS;
    }

    private static List<OrderStatusHistoryDto> mapOrderStatus(List<Order_status_history> orderStatusHistoryList) {
        List<OrderStatusHistoryDto> orderStatusHistoryDtos1 = new ArrayList<>();
        if (orderStatusHistoryList != null) {
            for (Order_status_history orderstatus : orderStatusHistoryList) {
                orderStatusHistoryDtos1.add(OrderStatusHistoryDto.fromEntity(orderstatus));
            }
        }
        return orderStatusHistoryDtos1;
    }

}
