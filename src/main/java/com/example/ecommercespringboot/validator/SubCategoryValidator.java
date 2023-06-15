package com.example.ecommercespringboot.validator;

import com.example.ecommercespringboot.dto.CategoryDto;
import com.example.ecommercespringboot.dto.Sub_categoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryValidator {

    public static List<String> validate(Sub_categoryDto dto) {
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
