package com.example.ecommercespringboot.dto;


import com.example.ecommercespringboot.models.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderStatusDto {

    private Integer id;
    private String name;

    public static OrderStatusDto fromEntity(OrderStatus orderStatus)
    {
        if (orderStatus == null) {return null;}

        return OrderStatusDto.builder()
                .id(orderStatus.getId())
                .name(orderStatus.getName())
                .build();
    }

    public static OrderStatus toEntity(OrderStatusDto orderStatusDto)
    {
        if (orderStatusDto == null) {return null;}

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(orderStatusDto.getId());
        orderStatus.setName(orderStatusDto.getName());

        return orderStatus;
    }
}
