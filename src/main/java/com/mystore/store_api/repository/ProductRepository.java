package com.mystore.store_api.repository;

import com.mystore.store_api.model.Product;
import com.mystore.store_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCode(String code);
}
