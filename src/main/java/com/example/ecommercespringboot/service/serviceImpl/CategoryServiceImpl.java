package com.example.ecommercespringboot.service.serviceImpl;

import com.example.ecommercespringboot.dto.CategoryDto;
import com.example.ecommercespringboot.exception.EntityNotFoundException;
import com.example.ecommercespringboot.exception.ErrorCodes;
import com.example.ecommercespringboot.exception.InvalidEntityException;
import com.example.ecommercespringboot.exception.UnauthorizedException;
import com.example.ecommercespringboot.jwt.JwtFilter;
import com.example.ecommercespringboot.models.Category;
import com.example.ecommercespringboot.models.Customer;
import com.example.ecommercespringboot.repository.CategoryRepository;
import com.example.ecommercespringboot.repository.CustomerRepository;
import com.example.ecommercespringboot.service.CategoryService;
import com.example.ecommercespringboot.validator.CategoryValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    JwtFilter jwtFilter;
    @Override
    public CategoryDto createCategory(CategoryDto dto) {

        if (jwtFilter.isAdmin())
        {
            List<String> errors = CategoryValidator.validate(dto);
            if (!errors.isEmpty())
            {
                log.error("customer is not valid", dto);
                throw new InvalidEntityException("this Category is not valid", ErrorCodes.CATEGORY_NOT_VALID);
            }
            return CategoryDto.fromEntity(categoryRepository.save(
                    CategoryDto.toEntity(dto)));
        }
        log.error("you're not an admin", dto);
        throw new UnauthorizedException("you're not authorized", ErrorCodes.NOT_AUTHORIZED);
    }
    @Override
    public CategoryDto findById(Integer id) {
        if (id == null) {
            log.error("Category ID is null");
            return null;
        }
        Optional<Category> category = categoryRepository.findById(id);
        return Optional.of(CategoryDto.fromEntity(category.get()))
                .orElseThrow(() -> new EntityNotFoundException(
                        "No articl with this ID"+ id + " found in DB"
                        , ErrorCodes.CATEGORY_NOT_FOUND)
                );
    }

    @Override
    public List<CategoryDto> findAll() {

        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

}
