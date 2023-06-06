package com.example.ecommercespringboot.dto;

import com.example.ecommercespringboot.models.Customer;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerDto {

    private Integer id;
    private String first_name;
    private String second_name;
    private String phone_number;
    private String address;
    private String email;
    private String password;
    private String role;
    private CartDto cart;

    public static CustomerDto fromEntity(Customer customer)
    {
        if (customer == null) {return null;}

        return CustomerDto.builder()
                .id(customer.getId())
                .first_name(customer.getFirst_name())
                .second_name(customer.getSecond_name())
                .phone_number(customer.getPhone_number())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .role(customer.getRole())
                .cart(CartDto.fromEntity(customer.getCart()))
                .build();
    }

    public static Customer toEntity(CustomerDto customerDto)
    {
        if (customerDto == null) {return null;}

        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirst_name(customerDto.getFirst_name());
        customer.setFirst_name(customerDto.getSecond_name());
        customer.setAddress(customerDto.getAddress());
        customer.setPhone_number(customerDto.getPhone_number());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setRole(customerDto.getRole());
        customer.setCart(CartDto.toEntity(customerDto.getCart()));

        return customer;
    }
}
