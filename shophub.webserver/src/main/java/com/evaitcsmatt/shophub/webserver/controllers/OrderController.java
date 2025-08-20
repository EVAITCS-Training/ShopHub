package com.evaitcsmatt.shophub.webserver.controllers;

import com.evaitcsmatt.shophub.webserver.dtos.OrderDto;
import com.evaitcsmatt.shophub.webserver.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/order/")
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto);
        return ResponseEntity.noContent().build();
    }
}
