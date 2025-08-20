package com.evaitcsmatt.shophub.webserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRowDto {
    private String productName;
    private double price;
    private long quantity;
}
