package com.mystore.store_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @NotBlank
    @Size(min = 3, max = 50, message = "The name must be at least 3 characters and no more than 50.")
    private String name;

    @NotBlank
    @Email
    @Size(min = 1, max = 150, message = "The email must be at least 1 characters and no more than 150.")
    private String email;

    @NotBlank
    @Size(min = 6, max = 20, message = "The password must be at least 6 characters and no more than 20.")
    private String password;
}
