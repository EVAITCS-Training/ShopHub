package com.evaitcsmatt.shophub.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaitcsmatt.shophub.webserver.entities.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
