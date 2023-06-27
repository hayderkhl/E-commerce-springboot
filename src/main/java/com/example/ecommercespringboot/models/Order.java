package com.example.ecommercespringboot.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "code")
    private String Code;

    @Column(name = "oderstatus")
    private String orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Order_product> orderProducts;

    @ManyToOne
    @JoinColumn(name = "customerid", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @Column(name = "orderdate")
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "cartid", referencedColumnName = "id", nullable = false)
    private Cart cart;

    @Column(name = "address")
    private String address;

}
