package com.example.ecommercespringboot.controller;

import com.example.ecommercespringboot.dto.CartDto;
import com.example.ecommercespringboot.dto.CategoryDto;
import com.example.ecommercespringboot.dto.CustomerDto;
import com.example.ecommercespringboot.service.CartService;
import com.example.ecommercespringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add/to/cart")
    public ResponseEntity<?> createCategory(@RequestBody CartDto dto)
    {
        return cartService.addToCart(dto);
    }

    @GetMapping("/get/customer/cart/{id}")
    public CartDto getCustomerById(@PathVariable Integer id)
    {
        return cartService.getCartByCustomerId(id);
    }

}
