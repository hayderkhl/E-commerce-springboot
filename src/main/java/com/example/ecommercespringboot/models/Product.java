package com.example.ecommercespringboot.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private @NotBlank String name;

    @Column(name = "code")
    private String code;

    @Column(name = "urlphoto", columnDefinition = "LONGTEXT")
    private String photo;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @ManyToOne
    @JoinColumn(name = "subcategoryid")
    private Sub_category subCategory;

    @Column(name = "description")
    private String description;

}
