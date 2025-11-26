package com.mystore.store_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @NotBlank
    @Email
    @Size(min = 1, max = 150)
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
