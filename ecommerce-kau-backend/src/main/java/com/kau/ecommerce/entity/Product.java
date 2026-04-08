package com.kau.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private Double price;
    private Double salePrice;
    private Boolean onSale;

    private Integer stock;

    private String imageUrl;

    @ManyToOne
    private Category category;

    private LocalDateTime createdAt = LocalDateTime.now();

}
