package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.models.Cart;
import com.example.ecommercespringboot.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT c FROM Order c WHERE c.customer.id = :customerId")
    Optional<Order> findByCustomerId(@Param("customerId") Integer customerId);
}
