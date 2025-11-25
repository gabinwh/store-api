package com.mystore.store_api.service;

import com.mystore.store_api.dto.ProductRequestDTO;
import com.mystore.store_api.dto.ProductResponseDTO;
import com.mystore.store_api.exception.ResourceNotFoundException;
import com.mystore.store_api.model.Category;
import com.mystore.store_api.model.Product;
import com.mystore.store_api.repository.CategoryRepository;
import com.mystore.store_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setValue(product.getValue());
        dto.setDescription(product.getDescription());
        if (product.getCategory() != null) {
            dto.setCategory(product.getCategory().getName());
        }
        return dto;
    }

    public List<ProductResponseDTO> listAllProducts() {
        return productRepository.findAll().stream().map(
                this::toResponseDTO
        ).collect(Collectors.toList());
    }

    public ProductResponseDTO create(ProductRequestDTO productRequestDTO){

        Category category = categoryRepository.findById(productRequestDTO.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + productRequestDTO.getCategoriaId()));

        Product product = new Product();
        product.setName(productRequestDTO.getNome());
        product.setValue(productRequestDTO.getValue());
        product.setDescription(productRequestDTO.getDescription());

        product.setCategory(category);

        Product productSave = productRepository.save(product);

        return toResponseDTO(productSave);
    }
}
