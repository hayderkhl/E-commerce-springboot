package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.models.Category;
import com.example.ecommercespringboot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepo;
    public void createCategory(Category category)
    {
        categoryRepo.save(category);
    }
}
