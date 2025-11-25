package com.mystore.store_api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {
    @NotBlank(message = "The name is required.")
    @Size(max = 255, message = "The maximum value is 255 characters.")
    private String nome;

    @NotNull(message = "The value is required.")
    @DecimalMin(value = "0.01", message = "The value must be greater than zero.")
    private BigDecimal value;

    @NotBlank(message = "The name is required.")
    @Size(max = 500, message = "The maximum value is 500 characters.")
    private String description;

    @NotNull(message = "The category is required.")
    private Long categoriaId;
}
