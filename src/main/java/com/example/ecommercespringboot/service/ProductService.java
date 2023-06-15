package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.ProductDto;
import com.example.ecommercespringboot.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {


    ProductDto createProduct(MultipartFile file,ProductDto dto);

    ProductDto findById(Integer id);

    List<ProductDto> findAll();

    Optional<Product> updateProductById(Integer productId, MultipartFile file, ProductDto dto);

}
