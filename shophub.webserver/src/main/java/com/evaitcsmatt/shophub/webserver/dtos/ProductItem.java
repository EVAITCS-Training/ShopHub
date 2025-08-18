package com.evaitcsmatt.shophub.webserver.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItem {
	private String name;
	private String description;
	private double price;
	private long quantity;
	private boolean isSeasonal;
	private String season;
	private LocalDateTime seasonBegin;
	private LocalDateTime seasonEnd;
}
