package com.example.ecommercespringboot.controller;

import com.example.ecommercespringboot.dto.CartDto;
import com.example.ecommercespringboot.dto.CategoryDto;
import com.example.ecommercespringboot.service.CartService;
import com.example.ecommercespringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
