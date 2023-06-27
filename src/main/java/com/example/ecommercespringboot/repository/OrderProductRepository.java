package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.models.Order_product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<Order_product, Integer> {
}
