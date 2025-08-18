package com.evaitcsmatt.shophub.webserver.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Size(min = 5)
	@NotBlank
	private String name;
	@Size(max = 300)
	@NotBlank
	private String description;
	@PositiveOrZero
	private double price;
	@PositiveOrZero
	private long quantity;
	private boolean isSeasonal;
	private String season;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime updatedAt;
	@Nullable
	private LocalDateTime seasonBegin;
	@Nullable
	private LocalDateTime seasonEnd;
	
}
