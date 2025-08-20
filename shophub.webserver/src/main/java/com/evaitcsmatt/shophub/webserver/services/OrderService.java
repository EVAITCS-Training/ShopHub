package com.evaitcsmatt.shophub.webserver.services;

import com.evaitcsmatt.shophub.webserver.dtos.OrderDto;
import com.evaitcsmatt.shophub.webserver.entities.Order;
import com.evaitcsmatt.shophub.webserver.entities.OrderRow;
import com.evaitcsmatt.shophub.webserver.repositories.OrderRepository;
import com.evaitcsmatt.shophub.webserver.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public void createOrder(OrderDto orderDto) {
        List<OrderRow> orderRows = new ArrayList<>();
        Order order = new Order();
        order.setTotal(orderDto.getTotal());
        orderDto.getOrderRowDtoList().forEach(orderRow -> {
            if(productRepository.existsByNameIgnoreCase(orderRow.getProductName())) {
                OrderRow oR = new OrderRow();
                oR.setOrder(order);
                oR.setProduct(productRepository.findByNameIgnoreCase(orderRow.getProductName()).orElse(null));
                oR.setQuantity(orderRow.getQuantity());
                oR.setTotal(orderRow.getPrice() * orderRow.getQuantity());
                orderRows.add(oR);
            }
        });
        order.setOrderRows(orderRows);
        orderRepository.save(order);
    }
}
