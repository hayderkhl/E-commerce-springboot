package com.example.ecommercespringboot.models;

import com.example.ecommercespringboot.dto.OrderStatusDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="order_status_History")
public class Order_status_history {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "orderid")
    private Order order;

    @Column(name = "from_status")
    private String from_status;

   @Column(name = "to_status")
    private String to_status;

}
