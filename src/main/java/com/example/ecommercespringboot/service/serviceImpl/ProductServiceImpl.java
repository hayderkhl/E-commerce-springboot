package com.example.ecommercespringboot.service.serviceImpl;

import com.example.ecommercespringboot.dto.ProductDto;
import com.example.ecommercespringboot.dto.Sub_categoryDto;
import com.example.ecommercespringboot.exception.EntityNotFoundException;
import com.example.ecommercespringboot.exception.ErrorCodes;
import com.example.ecommercespringboot.exception.InvalidEntityException;
import com.example.ecommercespringboot.exception.UnauthorizedException;
import com.example.ecommercespringboot.jwt.JwtFilter;
import com.example.ecommercespringboot.models.Product;
import com.example.ecommercespringboot.repository.ProductRepository;
import com.example.ecommercespringboot.repository.Sub_categoryRepository;
import com.example.ecommercespringboot.service.ProductService;
import com.example.ecommercespringboot.validator.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    JwtFilter jwtFilter;
    @Override
    public ProductDto createProduct(MultipartFile file,ProductDto dto) {
        if (jwtFilter.isAdmin())
        {
            List<String> errors = ProductValidator.validate(dto);
            if (!errors.isEmpty()) {
                log.error("Product is not valid: {}", dto);
                throw new InvalidEntityException("This product is not valid", ErrorCodes.PRODUCT_NOT_VALID);
            }

            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;
            String fileName = StringUtils.cleanPath(uniqueFileName);

            if (fileName.contains("..")) {
                throw new InvalidEntityException("Not a valid image file", ErrorCodes.IMAGE_FILE_NOT_VALID);
            }

            try {
                dto.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
                Product savedProduct = productRepository.save(ProductDto.toEntity(dto));
                return ProductDto.fromEntity(savedProduct);
            } catch (IOException e) {
                log.error("Error while saving the product: {}", dto, e);
                throw new RuntimeException("Failed to save the product");
            }
        }
        log.error("you're not an admin", dto);
        throw new UnauthorizedException("you're not authorized", ErrorCodes.NOT_AUTHORIZED);
    }

    @Override
    public ProductDto findById(Integer id) {
        if (id == null) {
            log.error("Product ID is null");
            return null;
        }
        Optional<Product> product = productRepository.findById(id);
        return Optional.of(ProductDto.fromEntity(product.get()))
                .orElseThrow(() -> new EntityNotFoundException(
                        "No articl with this ID"+ id + " found in DB"
                        , ErrorCodes.PRODUCT_NOT_FOUND)
                );
    }

    @Override
    public List<ProductDto> findAll() {

        return productRepository.findAll().stream()
                .map(ProductDto::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Product> updateProductById(Integer productId, MultipartFile file, ProductDto dto) {
       if (jwtFilter.isAdmin())
       {
           Product existingProduct = productRepository.findById(productId)
                   .orElseThrow(() -> new EntityNotFoundException("Product not found"));

           if (file != null) {
               String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
               String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;
               String fileName = StringUtils.cleanPath(uniqueFileName);

               if (fileName.contains("..")) {
                   throw new InvalidEntityException("Not a valid image file", ErrorCodes.IMAGE_FILE_NOT_VALID);
               }

               try {
                   dto.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
               } catch (IOException e) {
                   log.error("Error while processing the image file: {}", file.getOriginalFilename(), e);
                   throw new RuntimeException("Failed to process the image file");
               }
           }
           if (dto.getPrice() != null)
           {
               existingProduct.setPrice(dto.getPrice());
           }
           if (StringUtils.hasLength(dto.getName()))
           {
               existingProduct.setName(dto.getName());
           }

           if (StringUtils.hasLength(dto.getDescription()))
           {
               existingProduct.setDescription(dto.getDescription());
           }
           if (dto.getSubCategory() != null)
           {
               existingProduct.setSubCategory(Sub_categoryDto.toEntity(dto.getSubCategory()));
           }
           if (dto.getQuantity() != null)
           {
               existingProduct.setQuantity(dto.getQuantity());
           }

           try {
               productRepository.save(existingProduct);
               return Optional.of(existingProduct);
           } catch (Exception e) {
               log.error("Error while updating the product: {}", existingProduct, e);
               throw new RuntimeException("Failed to update the product");
           }
       }
        log.error("you're not an admin", dto);
        throw new UnauthorizedException("you're not authorized", ErrorCodes.NOT_AUTHORIZED);
    }
}
