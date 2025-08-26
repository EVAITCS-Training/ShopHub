package com.evaitcsmatt.shophub.webserver.services;

import java.util.ArrayList;
import java.util.List;

import com.evaitcsmatt.shophub.webserver.exceptions.ProductDuplitcationException;
import com.evaitcsmatt.shophub.webserver.exceptions.ProductNotFound;
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


	public void deleteProduct(String name){
		Product product = productRepository.findByNameIgnoreCase(name)
				.orElseThrow(() -> new ProductNotFound("Product not found"));
		productRepository.deleteByName(name);
	}

	public void updateProduct(String name, ProductItem updatedProduct){
		Product product = productRepository.findByNameIgnoreCase(name)
				.orElseThrow(() -> new ProductNotFound("Product not found"));
		product.setName(updatedProduct.getName());
		product.setDescription(updatedProduct.getDescription());
		product.setPrice(updatedProduct.getPrice());
		product.setQuantity(updatedProduct.getQuantity());
		product.setSeasonal(updatedProduct.isSeasonal());
		product.setSeason(updatedProduct.getSeason());
		product.setSeasonBegin(updatedProduct.getSeasonBegin());
		product.setSeasonEnd(updatedProduct.getSeasonEnd());

		productRepository.save(product);
	}
}
