package com.example.ecommercespringboot.dto;

import com.example.ecommercespringboot.models.Cart;
import com.example.ecommercespringboot.models.Product;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Set;

@Builder
@Data
public class CartDto {

    private Integer id;
    private BigDecimal total_cost;
    private CustomerDto customer;
    private Set<ProductDto> products;

    public static CartDto fromEntity(Cart cart)
    {
        if (cart == null) {return null;}

        return CartDto.builder()
                .id(cart.getId())
                .total_cost(cart.getTotal_cost())
                .customer(CustomerDto.fromEntity(cart.getCustomer()))
                .products((Set<ProductDto>) ProductDto.fromEntity((Product) cart.getProducts()))
                .build();
    }

    public static Cart toEntity(CartDto cartDto)
    {
        if (cartDto == null) {return null;}

        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setCustomer(CustomerDto.toEntity(cartDto.getCustomer()));
        cart.setProducts((Set<Product>) ProductDto.toEntity((ProductDto) cartDto.getProducts()));

        return cart;
    }

}
