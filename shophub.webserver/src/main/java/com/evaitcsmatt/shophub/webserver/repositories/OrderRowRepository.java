package com.evaitcsmatt.shophub.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaitcsmatt.shophub.webserver.entities.OrderRow;

public interface OrderRowRepository extends JpaRepository<OrderRow, Long> {

}
