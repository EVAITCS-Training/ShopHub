package com.evaitcsmatt.shoppinghub.console.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaitcsmatt.shoppinghub.console.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
