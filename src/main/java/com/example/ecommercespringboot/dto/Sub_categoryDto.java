package com.example.ecommercespringboot.dto;

import com.example.ecommercespringboot.models.Sub_category;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Sub_categoryDto {

    private Integer Id;
    private String name;
    private CategoryDto category;

    public static Sub_categoryDto fromEntity(Sub_category sub_category)
    {
        if (sub_category == null) {return null;}

        return Sub_categoryDto.builder()
                .Id(sub_category.getId())
                .name(sub_category.getName())
                .category(CategoryDto.fromEntity(sub_category.getCategory()))
                .build();
    }

    public static Sub_category toEntity(Sub_categoryDto subCategoryDto)
    {
        if (subCategoryDto == null) {return null;}

        Sub_category subCategory = new Sub_category();
        subCategory.setId(subCategoryDto.getId());
        subCategory.setName(subCategoryDto.getName());
        subCategory.setCategory(CategoryDto.toEntity(subCategoryDto.getCategory()));

        return subCategory;
    }

}
