package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.models.Cart_product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartProductRepository extends JpaRepository<Cart_product, Integer> {

}
