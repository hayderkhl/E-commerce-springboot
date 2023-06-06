package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
