package com.example.ecommercespringboot.validator;

import com.example.ecommercespringboot.dto.OrderStatusDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusValidator {

    public static List<String> validate(OrderStatusDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("please add a order statute ");
        }

        if (!StringUtils.hasLength(dto.getName())) {
            errors.add("add your name!");
        }

        return errors;
    }
}
