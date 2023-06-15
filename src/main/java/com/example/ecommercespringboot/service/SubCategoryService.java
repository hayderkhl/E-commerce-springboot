package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.Sub_categoryDto;

import java.util.List;

public interface SubCategoryService {

    Sub_categoryDto createSubCategory(Sub_categoryDto subCategoryDto);

    Sub_categoryDto getSubCategoryById(Integer id);

    List<Sub_categoryDto> findAll();
}
