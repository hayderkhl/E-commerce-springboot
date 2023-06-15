package com.example.ecommercespringboot.dto;

import com.example.ecommercespringboot.models.Category;
import com.example.ecommercespringboot.models.Customer;
import com.example.ecommercespringboot.models.Sub_category;
import com.example.ecommercespringboot.repository.Sub_categoryRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
@Data
public class CategoryDto {

    private Integer id;
    private String name;
    @JsonIgnore
    private List<Sub_categoryDto> subCategories;

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
