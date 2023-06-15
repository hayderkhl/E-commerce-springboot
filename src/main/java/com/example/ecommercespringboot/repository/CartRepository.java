package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.dto.CartDto;
import com.example.ecommercespringboot.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
