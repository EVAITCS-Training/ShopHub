package com.evaitcsmatt.shoppinghub.console.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaitcsmatt.shoppinghub.console.entities.Product;

/**
 * Repository interface for Product entities.
 * Extends JpaRepository to provide CRUD operations and custom queries for Product.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
    /**
     * Finds products with names matching the given pattern (SQL LIKE syntax).
     * @param name the pattern to match (e.g., "%Apple%")
     * @return list of matching products
     */
    List<Product> findByNameLike(String name);
}
