package com.evaitcsmatt.shophub.webserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private List<OrderRowDto> orderRowDtoList;
    private double total;
}
