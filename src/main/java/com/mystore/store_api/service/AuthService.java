package com.mystore.store_api.service;

import com.mystore.store_api.dto.LoginRequestDTO;
import com.mystore.store_api.dto.TokenResponseDTO;
import com.mystore.store_api.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtilsService jwtUtilsService;

    public TokenResponseDTO authenticateUser(LoginRequestDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();

        String jwt = jwtUtilsService.generateToken(user);

        TokenResponseDTO response = new TokenResponseDTO();
        response.setToken(jwt);
        response.setUsername(user.getName());

        return response;
    }
}
