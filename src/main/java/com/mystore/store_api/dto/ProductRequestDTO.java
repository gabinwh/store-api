package com.mystore.store_api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {
    @NotBlank(message = "The name is required.")
    @Size(max = 255, message = "The maximum value of the name is 255 characters.")
    private String name;

    @NotBlank(message = "The code is required.")
    @Size(max = 255, message = "The maximum value of the code is 255 characters.")
    private String code;

    @NotNull(message = "The value is required.")
    @DecimalMin(value = "0.01", message = "The value must be greater than zero.")
    private BigDecimal value;

    @NotBlank(message = "The description is required.")
    @Size(max = 500, message = "The maximum value of the description is 500 characters.")
    private String description;

    @NotNull(message = "The category is required.")
    private Long categoryId;

    @NotNull(message = "The inventory is required.")
    @PositiveOrZero(message = "Inventory must positive")
    @Max(value = 1000000, message = "Inventory cannot be greater than 1000000")
    private int inventory;
}
