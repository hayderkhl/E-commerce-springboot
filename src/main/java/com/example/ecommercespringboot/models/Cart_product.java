package com.example.ecommercespringboot.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="cart_product")
public class Cart_product
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idCart")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "idProduct" )
    private Product product;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "total_cost")
    private BigDecimal total_cost;
}
