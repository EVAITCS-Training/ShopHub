package com.evaitcsmatt.shophub.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaitcsmatt.shophub.webserver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
