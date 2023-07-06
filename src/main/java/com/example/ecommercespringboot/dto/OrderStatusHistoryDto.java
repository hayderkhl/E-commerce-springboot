package com.example.ecommercespringboot.dto;

import com.example.ecommercespringboot.models.Order_status_history;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderStatusHistoryDto {

    private Integer id;
    @JsonIgnore
    private OrderDto order;
    private String from_status;
    private String to_status;

    public static OrderStatusHistoryDto fromEntity(Order_status_history orderStatusHistory)
    {
        if (orderStatusHistory == null) {return null;}

        return OrderStatusHistoryDto.builder()
                .id(orderStatusHistory.getId())
                .from_status(orderStatusHistory.getFrom_status())
                .to_status(orderStatusHistory.getTo_status())
                .build();
    }

    public static Order_status_history toEntity(OrderStatusHistoryDto orderStatusHistoryDto)
    {
        if (orderStatusHistoryDto == null) {return null;}

        Order_status_history orderStatusHistory = new Order_status_history();
        orderStatusHistory.setId(orderStatusHistoryDto.getId());
        orderStatusHistory.setFrom_status(orderStatusHistoryDto.getFrom_status());
        orderStatusHistory.setTo_status(orderStatusHistoryDto.getTo_status());

        return orderStatusHistory;
    }
}
