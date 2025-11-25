package com.mystore.store_api.controller;

import com.mystore.store_api.dto.LoginRequestDTO;
import com.mystore.store_api.dto.RegisterRequestDTO;
import com.mystore.store_api.dto.TokenResponseDTO;
import com.mystore.store_api.dto.UserResponseDTO;
import com.mystore.store_api.service.AuthService;
import com.mystore.store_api.service.JwtUtilsService;
import com.mystore.store_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(
            UserService userService,
            AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody RegisterRequestDTO registerRequest) {

        UserResponseDTO registeredUser = userService.registerNewUser(registerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {

        TokenResponseDTO tokenResponse = authService.authenticateUser(loginRequest);

        return ResponseEntity.ok(tokenResponse);
    }
}
