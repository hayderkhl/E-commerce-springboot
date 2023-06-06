package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
