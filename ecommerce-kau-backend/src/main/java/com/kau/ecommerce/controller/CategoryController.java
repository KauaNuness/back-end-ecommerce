package com.kau.ecommerce.controller;

import com.kau.ecommerce.entity.Category;
import com.kau.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository repo;

    @PostMapping
    public Category create(@RequestBody Category c){
        return repo.save(c);
    }

    @GetMapping
    public List<Category> list(){
        return repo.findAll();
    }

}
