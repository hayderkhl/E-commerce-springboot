package com.example.ecommercespringboot.service.serviceImpl;

import com.example.ecommercespringboot.dto.CategoryDto;
import com.example.ecommercespringboot.dto.Sub_categoryDto;
import com.example.ecommercespringboot.exception.EntityNotFoundException;
import com.example.ecommercespringboot.exception.ErrorCodes;
import com.example.ecommercespringboot.exception.InvalidEntityException;
import com.example.ecommercespringboot.exception.UnauthorizedException;
import com.example.ecommercespringboot.jwt.JwtFilter;
import com.example.ecommercespringboot.models.Category;
import com.example.ecommercespringboot.repository.CategoryRepository;
import com.example.ecommercespringboot.repository.Sub_categoryRepository;
import com.example.ecommercespringboot.service.SubCategoryService;
import com.example.ecommercespringboot.validator.SubCategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private Sub_categoryRepository sub_categoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    JwtFilter jwtFilter;

    public SubCategoryServiceImpl(Sub_categoryRepository sub_categoryRepository, CategoryRepository categoryRepository) {
        this.sub_categoryRepository = sub_categoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Sub_categoryDto createSubCategory(Sub_categoryDto dto) {

        if (jwtFilter.isAdmin())
        {
            List<String> errors = SubCategoryValidator.validate(dto);
            if (!errors.isEmpty())
            {
                log.error("customer is not valid", dto);
                throw new InvalidEntityException("this SubCategory is not valid", ErrorCodes.SUBCATEGORY_NOT_VALID);
            }

            if(!isCategoryExist(dto.getCategory().getId()))
            {
                log.error("this category does not exist", dto);
                throw new InvalidEntityException("this category does not exist", ErrorCodes.CATEGORY_DOES_NOT_EXIST);
            }

            Category category = categoryRepository.findById(dto.getCategory().getId()).get();
            dto.setCategory(CategoryDto.fromEntity(category));

            return Sub_categoryDto.fromEntity(sub_categoryRepository.save(Sub_categoryDto.toEntity(dto)));

        }
        log.error("you're not an admin", dto);
        throw new UnauthorizedException("you're not authorized", ErrorCodes.NOT_AUTHORIZED);
        }

    @Override
    public Sub_categoryDto getSubCategoryById(Integer id) {
        if (id == null) {
            log.error("Category ID is null");
            return null;
        }
        return sub_categoryRepository.findById(id)
                .map(Sub_categoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune sub_category avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.Sub_CATEGORY_NOT_FOUND)
                );
    }

    @Override
    public List<Sub_categoryDto> findAll() {
        return sub_categoryRepository.findAll().stream()
                .map(Sub_categoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    private boolean isCategoryExist(Integer id) { return categoryRepository.existsById(id);
    }

}
