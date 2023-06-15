package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);

    @Query("select c from Customer c where c.email=:email")
    Customer findByEmailId(@Param("email") String email);

}
