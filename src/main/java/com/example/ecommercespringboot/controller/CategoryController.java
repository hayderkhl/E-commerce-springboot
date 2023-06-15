package com.example.ecommercespringboot.controller;

import com.example.ecommercespringboot.dto.CategoryDto;
import com.example.ecommercespringboot.models.Category;
import com.example.ecommercespringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public CategoryDto createCategory(@RequestBody CategoryDto dto)
    {
        return categoryService.createCategory(dto);
    }

    @GetMapping("/get/{categoryId}")
    public CategoryDto findById(@PathVariable("categoryId") Integer id) {return categoryService.findById(id);}

    @GetMapping("/get")
    public List<CategoryDto> findAll() {return categoryService.findAll();}

}
