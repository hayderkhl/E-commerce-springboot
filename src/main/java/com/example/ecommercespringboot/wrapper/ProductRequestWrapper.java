package com.example.ecommercespringboot.wrapper;

import com.example.ecommercespringboot.dto.ProductDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ProductRequestWrapper {

    private MultipartFile file;
    private ProductDto productDto;
}
