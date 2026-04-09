package com.kau.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private Double salePrice;
    private Boolean onSale;
    private String imageUrl;
    private String category;
}
