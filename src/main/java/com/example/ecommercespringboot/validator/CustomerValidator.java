package com.example.ecommercespringboot.validator;

import com.example.ecommercespringboot.dto.CustomerDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerValidator {

    public static List<String> validate(CustomerDto dto)
    {
        List<String> errors = new ArrayList<>();

        if (dto == null)
        {
            errors.add("please add a customer");
        }

        if (!StringUtils.hasLength(dto.getFirst_name()))
        {
            errors.add("add your name!");
        }
        if (!StringUtils.hasLength(dto.getEmail()))
        {
            errors.add("add your Email!");
        }
        if (!StringUtils.hasLength(dto.getPassword()))
        {
            errors.add("add your Password!");
        }
        if (!StringUtils.hasLength(dto.getAddress()))
        {
            errors.add("add your Address!");
        }
        return errors;
    }
}
