package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.CategoryDto;

import java.util.List;


public interface CategoryService {

     CategoryDto createCategory(CategoryDto category);

     CategoryDto findById(Integer id);

     List<CategoryDto> findAll();
}
