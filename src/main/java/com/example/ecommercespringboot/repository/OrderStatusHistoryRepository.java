package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.models.Order;
import com.example.ecommercespringboot.models.Order_status_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderStatusHistoryRepository extends JpaRepository<Order_status_history, Integer> {

//    @Query("SELECT osh FROM Order_status_history osh WHERE osh.order.id = :orderid")
//    List<Order_status_history> findAllByOrderId(@Param("orderid") Integer orderid);

    List<Order_status_history> findAllByOrderId(Integer orderid);

    Optional<Order_status_history> findById(Integer id);
}
