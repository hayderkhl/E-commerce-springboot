package com.example.ecommercespringboot.dto;

import com.example.ecommercespringboot.models.Category;
import com.example.ecommercespringboot.models.Customer;
import com.example.ecommercespringboot.models.Sub_category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryDto {

    private Integer id;
    private String name;
    @JsonIgnore
    private List<Sub_category> subCategories;

    public static CategoryDto fromEntity(Category category)
    {
        if (category == null) {return null;}

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                //we have to add cart
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto)
    {
        if (categoryDto == null) {return null;}

        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        // we have to add the cart

        return category;
    }
}
