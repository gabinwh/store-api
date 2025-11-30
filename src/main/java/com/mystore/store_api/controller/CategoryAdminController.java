package com.mystore.store_api.controller;

import com.mystore.store_api.dto.CategoryRequestDTO;
import com.mystore.store_api.dto.CategoryResponseDTO;
import com.mystore.store_api.dto.ProductRequestDTO;
import com.mystore.store_api.dto.ProductResponseDTO;
import com.mystore.store_api.service.CategoryService;
import com.mystore.store_api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/admin/category")
@RestController
@RequiredArgsConstructor
public class CategoryAdminController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO categoryResponseDTO = categoryService.create(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponseDTO);

    }
}
