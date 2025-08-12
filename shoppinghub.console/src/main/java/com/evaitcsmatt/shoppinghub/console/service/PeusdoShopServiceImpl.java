package com.evaitcsmatt.shoppinghub.console.service;

import org.springframework.stereotype.Service;

import com.evaitcsmatt.shoppinghub.console.entities.Product;
import com.evaitcsmatt.shoppinghub.console.entities.ShoppingCart;
import com.evaitcsmatt.shoppinghub.console.entities.Store;
import com.evaitcsmatt.shoppinghub.console.exceptions.ProductNotFoundException;
import com.evaitcsmatt.shoppinghub.console.repository.ProductRepository;

@Service
public class PeusdoShopServiceImpl implements ShopService {
	
	private ProductRepository productRepository;
	private Store store;
	private ShoppingCart shoppingCart;
	private boolean didStoreUpdate;
	
	public PeusdoShopServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
		this.store = new Store();
		this.shoppingCart = new ShoppingCart();
		this.didStoreUpdate = false;
		System.out.println("filling store");
		this.productRepository.findAll().forEach(item -> {
			this.store.addProduct(item, item.getQuantity());
		});
	}

	@Override
	public void addItemToCart(Product product, int quantity) {
		if(!store.hasProduct(product)) {
			throw new ProductNotFoundException("Product with name of " + product.getName() + " not found!");
		}
		shoppingCart.addItem(product, quantity);
		store.getStoreInventory().merge(product, (0 - quantity), Integer::sum);
	}

	@Override
	public void removeItemFromCart(Product product, int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProductToInventory(Product product, double price, int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProductFromInventory(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void completeOrder(ShoppingCart cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearCart() {
		shoppingCart.clear();
	}

	@Override
	public void updateQuantityInCart(Product product, int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayShop() {
		store.displayProducts();
	}

}
