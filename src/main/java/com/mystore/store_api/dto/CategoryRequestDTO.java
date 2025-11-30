package com.mystore.store_api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CategoryRequestDTO {
    @NotBlank(message = "The name is required.")
    @Size(max = 255, message = "The maximum value of the name is 255 characters.")
    private String name;
}
