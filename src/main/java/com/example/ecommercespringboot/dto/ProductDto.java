package com.example.ecommercespringboot.dto;

import com.example.ecommercespringboot.models.Product;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class ProductDto {

    private Integer id;
    private String name;
    private String code;
    private String photo;
    private BigDecimal price;
    private BigDecimal quantity;
    private Sub_categoryDto subCategory;
    private String description;

    public static ProductDto fromEntity(Product product)
    {
        if (product == null) {return null;}

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .price(product.getPrice())
                .photo(product.getPhoto())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .subCategory(Sub_categoryDto.fromEntity(product.getSubCategory()))
                .build();
    }

    public static Product toEntity(ProductDto productDto)
    {
        if (productDto == null) {return null;}

        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCode(productDto.getCode());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setPhoto(productDto.getPhoto());
        product.setSubCategory(Sub_categoryDto.toEntity(productDto.getSubCategory()));

        return product;
    }
}
