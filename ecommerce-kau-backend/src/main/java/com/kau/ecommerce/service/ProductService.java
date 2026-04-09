package com.kau.ecommerce.service;

import com.kau.ecommerce.dto.ProductRequestDTO;
import com.kau.ecommerce.dto.ProductResponseDTO;
import com.kau.ecommerce.entity.Category;
import com.kau.ecommerce.entity.Product;
import com.kau.ecommerce.repository.CategoryRepository;
import com.kau.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductResponseDTO create(ProductRequestDTO dto, String imageUrl){

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow();

        if (Boolean.TRUE.equals((dto.getOnSale()))){
            if (dto.getSalePrice() == null || dto.getSalePrice() >= dto.getPrice()){
                throw new RuntimeException("Promoção inválida");
            }
        }

        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setSalePrice(dto.getSalePrice());
        p.setOnSale(dto.getOnSale());
        p.setStock(dto.getStock());
        p.setCategory(category);
        p.setImageUrl(imageUrl);

        productRepository.save(p);

        return ProductResponseDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .price(p.getPrice())
                .salePrice(p.getSalePrice())
                .onSale(p.getOnSale())
                .imageUrl(p.getImageUrl())
                .category(category.getName())
                .build();
    }

    public List<ProductResponseDTO> getAll(){
        return productRepository.findAll()
                .stream()
                .map(p -> ProductResponseDTO.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .price(p.getPrice())
                        .salePrice(p.getSalePrice())
                        .onSale(p.getOnSale())
                        .imageUrl(p.getImageUrl())
                        .category(p.getCategory().getName())
                        .build())
                .toList();
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

}
