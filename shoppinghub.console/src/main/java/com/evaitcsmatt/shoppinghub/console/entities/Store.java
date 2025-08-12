package com.evaitcsmatt.shoppinghub.console.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Store {
	private Map<Product, Integer> products;
	
	public Store() {
		products = new HashMap<>();
	}
	
	public Store(Map<Product, Integer> products) {
		this.products = products;
	}
	
	public void addProduct(Product product, int quantity) {
		products.put(product, quantity);
	}
	
	public void displayProducts() {
		for (Product product : products.keySet()) {
			System.out.println(product.getName() + ", " + product.getPrice() + ", " + products.get(product));
		}
	}
	
	public Map<Product, Integer> getStoreInventory() {
		return products;
	}
	
	public boolean hasProduct(Product product) {
		return products.containsKey(product);
	}

	@Override
	public int hashCode() {
		return Objects.hash(products);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		return Objects.equals(products, other.products);
	}

	@Override
	public String toString() {
		return "Store [products=" + products + "]";
	}
}
