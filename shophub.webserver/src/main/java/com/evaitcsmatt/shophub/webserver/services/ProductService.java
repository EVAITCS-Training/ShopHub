package com.evaitcsmatt.shophub.webserver.services;

import java.util.ArrayList;
import java.util.List;

import com.evaitcsmatt.shophub.webserver.exceptions.ProductDuplitcationException;
import com.evaitcsmatt.shophub.webserver.utils.ProductMapper;
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
	@Autowired
	private ProductMapper productMapper;
	
	public void createProduct(PostNewProduct product) {
		if (productRepository.existsByNameIgnoreCase(product.getName())) {
			throw new ProductDuplitcationException("Product with the name " + product.getName() + " already exists!");
		}
		Product newProduct = productMapper.postNewProductToProduct(product);
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
