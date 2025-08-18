package com.evaitcsmatt.shophub.webserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostNewProduct {
	private String name;
	private String description;
	private double price;
	private long quantity;
}
