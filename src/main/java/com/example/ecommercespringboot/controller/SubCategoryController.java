package com.example.ecommercespringboot.controller;

import com.example.ecommercespringboot.dto.CategoryDto;
import com.example.ecommercespringboot.dto.Sub_categoryDto;
import com.example.ecommercespringboot.models.Sub_category;
import com.example.ecommercespringboot.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subCategory")
public class SubCategoryController {

    private SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @PostMapping("/create")
    public Sub_categoryDto createCategory(@RequestBody Sub_categoryDto dto)
    {
       return subCategoryService.createSubCategory(dto);
    }

    @GetMapping("/get/{id}")
    public Sub_categoryDto getCategory(@PathVariable Integer id)
    {
        return subCategoryService.getSubCategoryById(id);
    }

    @GetMapping("/get")
    public List<Sub_categoryDto> findAll() {return subCategoryService.findAll();}
}
