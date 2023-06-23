package com.example.ecommercespringboot.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "total_cost")
    private BigDecimal total_cost;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private List<Cart_product> cartProducts;

}
