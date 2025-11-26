package com.mystore.store_api.service;

import com.mystore.store_api.dto.RegisterRequestDTO;
import com.mystore.store_api.dto.UserResponseDTO;
import com.mystore.store_api.model.User;
import com.mystore.store_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }


    public UserResponseDTO registerNewUser(RegisterRequestDTO dto) {

        User newUser = new User();

        newUser.setName(dto.getName());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newUser.setRole("CLIENTE");

        User userSaved = userRepository.save(newUser);
        return toResponseDTO(userSaved);
    }
}