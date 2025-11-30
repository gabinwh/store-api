package com.mystore.store_api.config.security;

import com.mystore.store_api.service.CustomUserDetailsService;
import com.mystore.store_api.service.JwtUtilsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource;

    public SecurityConfig(CorsConfigurationSource corsConfigurationSource) {
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtUtilsService jwtUtilsService, CustomUserDetailsService customUserDetailsService) {
        return new JwtAuthFilter(jwtUtilsService, customUserDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/product").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/admin/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/admin/product").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/admin/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/admin/product/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/admin/category").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )


                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // --- 4. Bean para o Codificador de Senha (BCrypt) ---
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // --- 5. Bean para o Gerenciador de Autenticação ---
    // Necessário para fazer o login manual no AuthController.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
