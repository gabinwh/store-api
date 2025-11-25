package com.mystore.store_api.dto;

import lombok.Data;

@Data
public class TokenResponseDTO {
    private String token;
    private String type = "Bearer";
    private String username;
    private Long id;
}
