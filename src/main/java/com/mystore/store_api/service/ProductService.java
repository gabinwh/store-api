package com.mystore.store_api.service;

import com.mystore.store_api.dto.CategoryResponseDTO;
import com.mystore.store_api.dto.ProductRequestDTO;
import com.mystore.store_api.dto.ProductResponseDTO;
import com.mystore.store_api.exception.BusinessException;
import com.mystore.store_api.exception.ResourceNotFoundException;
import com.mystore.store_api.model.Category;
import com.mystore.store_api.model.Inventory;
import com.mystore.store_api.model.Product;
import com.mystore.store_api.repository.CategoryRepository;
import com.mystore.store_api.repository.InventoryRepository;
import com.mystore.store_api.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final InventoryService inventoryService;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, InventoryService inventoryService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.inventoryService = inventoryService;
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setValue(product.getValue());
        dto.setDescription(product.getDescription());
        dto.setCode(product.getCode());
        if (product.getCategory() != null) {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setName(product.getCategory().getName());
            categoryResponseDTO.setId(product.getCategory().getId());
            dto.setCategory(categoryResponseDTO);
        }

        if (product.getInventory() != null) {
            dto.setQuantityInventory(product.getInventory().getQuantity());
            dto.setQuantityReservedInventory(product.getInventory().getReservedQuantity());
        }
        return dto;
    }

    public List<ProductResponseDTO> listAllProducts() {
        return productRepository.findAll().stream().map(
                this::toResponseDTO
        ).collect(Collectors.toList());
    }

    @Transactional
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO){

        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + productRequestDTO.getCategoryId()));

        productRepository.findByCode(productRequestDTO.getCode()).ifPresent(p -> {
            throw new BusinessException("A product with that code already exists: " + productRequestDTO.getCode());
        });

        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setValue(productRequestDTO.getValue());
        product.setDescription(productRequestDTO.getDescription());
        product.setCategory(category);
        product.setCode(productRequestDTO.getCode());
        Product productSaved = productRepository.save(product);

        inventoryService.create(productSaved, productRequestDTO.getInventory());

        return toResponseDTO(productSaved);
    }
}
