package com.evaitcsmatt.shophub.webserver.controllers;

import java.util.List;

import com.evaitcsmatt.shophub.webserver.dtos.PostNewProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.evaitcsmatt.shophub.webserver.dtos.ProductItem;
import com.evaitcsmatt.shophub.webserver.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	
	@GetMapping(value = {"/"})
	public ResponseEntity<List<ProductItem>> getStoreIndex() {
		List<ProductItem> items = productService.getAllProducts();
		return ResponseEntity.ok(items);
	}

	@PostMapping(value = {"/add", "/add/"})
	public ResponseEntity<Void> getStoreAddProduct(@RequestBody PostNewProduct product) {
		productService.createProduct(product);
		return ResponseEntity.noContent().build();
	}
}
