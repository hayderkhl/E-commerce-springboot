package com.example.ecommercespringboot.controller;

import com.example.ecommercespringboot.dto.ProductDto;
import com.example.ecommercespringboot.models.Product;
import com.example.ecommercespringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestParam("file") MultipartFile file,
                                                    @ModelAttribute ProductDto productDto) {
        ProductDto createdProduct = productService.createProduct(file, productDto);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/get/{id}")
    ProductDto findById(@PathVariable Integer id)
    {
        return productService.findById(id);
    }

    @GetMapping("/get")
    List<ProductDto> findAll()
    {
        return productService.findAll();
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Optional<Product>> updateProduct(
            @PathVariable Integer productId,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestBody ProductDto dto
    ) {
        Optional<Product> updatedProduct = productService.updateProductById(productId, file, dto);
        return ResponseEntity.ok(updatedProduct);
    }


}
