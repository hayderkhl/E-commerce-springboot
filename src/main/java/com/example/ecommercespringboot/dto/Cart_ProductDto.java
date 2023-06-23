package com.example.ecommercespringboot.dto;

import com.example.ecommercespringboot.models.Cart;
import com.example.ecommercespringboot.models.Cart_product;
import com.example.ecommercespringboot.models.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Builder
@Data
public class Cart_ProductDto {

    private Integer id;
    @JsonIgnore
    private CartDto cart;
    private BigDecimal quantity;
    private BigDecimal total_cost;
    private ProductDto product;

    public static Cart_ProductDto fromEntity(Cart_product cartProduct)
    {
        if (cartProduct == null) {return null;}

        return Cart_ProductDto.builder()
                .id(cartProduct.getId())
                .total_cost(cartProduct.getTotal_cost())
                .product(ProductDto.fromEntity(cartProduct.getProduct()))
                //.cart(CartDto.fromEntity(cartProduct.getCart()))
                .quantity(cartProduct.getQuantity())
                .build();
    }

    public static Cart_product toEntity(Cart_ProductDto cartProductDto)
    {
        if (cartProductDto == null) {return null;}

        Cart_product cartProduct = new Cart_product();
        cartProduct.setId(cartProductDto.getId());
        cartProduct.setCart(CartDto.toEntity(cartProductDto.getCart()));
        cartProduct.setProduct(ProductDto.toEntity(cartProductDto.getProduct()));
        cartProduct.setQuantity(cartProductDto.getQuantity());
        cartProduct.setTotal_cost(cartProductDto.getTotal_cost());

        return cartProduct;
    }

}
