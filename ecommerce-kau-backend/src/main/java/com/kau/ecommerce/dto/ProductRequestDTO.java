package com.kau.ecommerce.dto;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;
    private String description;
    private Double price;
    private Double salePrice;
    private Boolean onSale;
    private Integer stock;
    private Long categoryId;
}
