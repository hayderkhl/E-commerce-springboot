package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
}
