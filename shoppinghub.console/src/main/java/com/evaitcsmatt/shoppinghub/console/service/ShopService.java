package com.evaitcsmatt.shoppinghub.console.service;

import com.evaitcsmatt.shoppinghub.console.entities.Product;
import com.evaitcsmatt.shoppinghub.console.entities.ShoppingCart;

public interface ShopService {
	void addItemToCart(Product product, int quantity);
	void removeItemFromCart(Product product, int quantity);
	void addProductToInventory(Product product, double price, int quantity);
	void removeProductFromInventory(Product product);
	void completeOrder(ShoppingCart cart);
	void clearCart();
	void updateQuantityInCart(Product product, int quantity);
	void displayShop();
}
