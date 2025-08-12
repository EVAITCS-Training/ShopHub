package com.evaitcsmatt.shoppinghub.console.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaitcsmatt.shoppinghub.console.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByNameLike(String name);
}
