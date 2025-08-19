package com.evaitcsmatt.shophub.webserver.controllers;

import java.util.List;

import com.evaitcsmatt.shophub.webserver.dtos.PostNewProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.evaitcsmatt.shophub.webserver.dtos.ProductItem;
import com.evaitcsmatt.shophub.webserver.services.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	
	@GetMapping(value = {"/store", "/store/"})
	public String getStoreIndex(Model model) {
		List<ProductItem> items = productService.getAllProducts();
		model.addAttribute("products", items);
		return "store-index";
	}

	@GetMapping(value = {"/store/add", "/store/add/"})
	public String getStoreAddProduct(Model model) {
		model.addAttribute("product", new PostNewProduct());
		return "store-add";
	}
}
