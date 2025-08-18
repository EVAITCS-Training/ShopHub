package com.evaitcsmatt.shophub.webserver.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaitcsmatt.shophub.webserver.dtos.PostNewProduct;
import com.evaitcsmatt.shophub.webserver.dtos.ProductItem;
import com.evaitcsmatt.shophub.webserver.entities.Product;
import com.evaitcsmatt.shophub.webserver.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public void createProduct(PostNewProduct product) {
		Product newProduct = Product.builder()
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.quantity(product.getQuantity())
				.build();
		productRepository.save(newProduct);
	}
	
	public List<ProductItem> getAllProducts() {
		List<ProductItem> items = new ArrayList<>();
		productRepository.findAll().forEach(item ->{
			items.add(new ProductItem(
					item.getName(), 
					item.getDescription(), 
					item.getPrice(), 
					item.getQuantity(), 
					item.isSeasonal(), 
					item.getSeason(), 
					item.getSeasonBegin(), 
					item.getSeasonEnd()));
		});
		return items;
	}
	
}
