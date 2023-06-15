package com.example.ecommercespringboot.validator;

import com.example.ecommercespringboot.dto.ProductDto;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class ProductValidator {

    public static List<String> validate(ProductDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("please add a customer");
        }

        if (!StringUtils.hasLength(dto.getName())) {
            errors.add("add your name!");
        }
        if (!StringUtils.hasLength(dto.getCode())) {
            errors.add("add your code!");
        }
        if (!StringUtils.hasLength(dto.getDescription())) {
            errors.add("add your descreption!");
        }

        return errors;
    }
}