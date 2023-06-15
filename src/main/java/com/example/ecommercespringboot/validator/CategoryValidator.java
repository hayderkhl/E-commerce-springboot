package com.example.ecommercespringboot.validator;

import com.example.ecommercespringboot.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate(CategoryDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("please add a category");
        }

        if (!StringUtils.hasLength(dto.getName())) {
            errors.add("add your name!");
        }


        return errors;
    }
}