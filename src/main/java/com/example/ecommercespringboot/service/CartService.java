package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.CartDto;
import com.example.ecommercespringboot.models.Product;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addToCart(CartDto cartDto);
}
