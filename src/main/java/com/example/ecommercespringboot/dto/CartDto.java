package com.example.ecommercespringboot.dto;

import com.example.ecommercespringboot.models.Cart;
import com.example.ecommercespringboot.models.Cart_product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Builder
@Getter
@Setter
public class CartDto {

    private Integer id;
    private BigDecimal total_cost;
    private Integer customerid;

    private CustomerDto customer;
    private List<Cart_ProductDto> cartProducts;


    public static CartDto fromEntity(Cart cart)
    {
        if (cart == null) {return null;}

        return CartDto.builder()
                .id(cart.getId())
                .total_cost(cart.getTotal_cost())
                .customerid(cart.getCustomer().getId())
               // .customer(CustomerDto.fromEntity(cart.getCustomer()))
                .cartProducts(mapCartProducts(cart.getCartProducts()))
                .build();
    }

    public static Cart toEntity(CartDto cartDto)
    {
        if (cartDto == null) {return null;}

        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setCustomer(CustomerDto.toEntity(cartDto.getCustomer()));


        return cart;
    }

    private static List<Cart_ProductDto> mapCartProducts(List<Cart_product> cartProducts) {
        List<Cart_ProductDto> cartProductDtos = new ArrayList<>();
        if (cartProducts != null) {
            for (Cart_product cartProduct : cartProducts) {
                cartProductDtos.add(Cart_ProductDto.fromEntity(cartProduct));
            }
        }
        return cartProductDtos;
    }
}
