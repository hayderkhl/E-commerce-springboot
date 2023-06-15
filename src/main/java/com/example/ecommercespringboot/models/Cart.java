package com.example.ecommercespringboot.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

import java.util.Set;

@Data
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


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "cart_product",
            joinColumns = {
                    @JoinColumn(name = "cartid", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "productid", referencedColumnName = "id")
            }
    )
    private Set<Product> products;
}
