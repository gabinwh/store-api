package com.mystore.store_api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private BigDecimal value;
    private String description;
    private String category;
}
