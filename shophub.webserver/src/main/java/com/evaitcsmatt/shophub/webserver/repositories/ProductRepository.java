package com.evaitcsmatt.shophub.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaitcsmatt.shophub.webserver.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
