package com.kau.ecommerce.controller;

import com.kau.ecommerce.dto.ProductRequestDTO;
import com.kau.ecommerce.dto.ProductResponseDTO;
import com.kau.ecommerce.service.ImageService;
import com.kau.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ImageService imageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductResponseDTO create(
            @RequestPart("data") ProductRequestDTO dto,
            @RequestPart("file")MultipartFile file
            ){
        String url = imageService.upload(file);
        return service.create(dto, url);
    }

    @GetMapping
    public List<ProductResponseDTO> getAll(){
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
