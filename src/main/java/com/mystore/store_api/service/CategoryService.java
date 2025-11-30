package com.mystore.store_api.service;

import com.mystore.store_api.dto.CategoryRequestDTO;
import com.mystore.store_api.dto.CategoryResponseDTO;
import com.mystore.store_api.exception.BusinessException;
import com.mystore.store_api.model.Category;
import com.mystore.store_api.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private CategoryResponseDTO toResponseDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }


    public CategoryResponseDTO create(CategoryRequestDTO requestDTO) {
        if (categoryRepository.existsByName(requestDTO.getName())) {
            throw new BusinessException("Category already exists.");
        }

        Category category = new Category();
        category.setName(requestDTO.getName());
        Category categorySaved = categoryRepository.save(category);

        return toResponseDTO(categorySaved);
    }
}
