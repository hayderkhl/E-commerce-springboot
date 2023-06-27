package com.example.ecommercespringboot.dto;

import com.example.ecommercespringboot.models.Cart_product;
import com.example.ecommercespringboot.models.Order_product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Order_productDTO {

    private Integer id;
    @JsonIgnore
    private OrderDto order;
    private BigDecimal quantity;
    private BigDecimal total_cost;
    private ProductDto product;

    public static Order_productDTO fromEntity(Order_product orderProduct)
    {
        if (orderProduct == null) {return null;}

        return Order_productDTO.builder()
                .id(orderProduct.getId())
                .total_cost(orderProduct.getTotal_cost())
                .product(ProductDto.fromEntity(orderProduct.getProduct()))
                //.cart(CartDto.fromEntity(cartProduct.getCart()))
                .quantity(orderProduct.getQuantity())
                .build();
    }

    public static Order_product toEntity(Order_productDTO orderProductDTO)
    {
        if (orderProductDTO == null) {return null;}

        Order_product orderProduct = new Order_product();
        orderProduct.setId(orderProductDTO.getId());
        orderProduct.setOrder(OrderDto.toEntity(orderProductDTO.getOrder()));
        orderProduct.setProduct(ProductDto.toEntity(orderProductDTO.getProduct()));
        orderProduct.setQuantity(orderProductDTO.getQuantity());
        orderProduct.setTotal_cost(orderProductDTO.getTotal_cost());

        return orderProduct;
    }
}
