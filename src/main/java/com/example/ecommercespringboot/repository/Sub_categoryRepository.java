package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.models.Sub_category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.FutureOrPresent;
import java.util.List;

public interface Sub_categoryRepository extends JpaRepository<Sub_category, Integer> {

    List<Sub_category> findAllByCategoryId(Integer id);
}
