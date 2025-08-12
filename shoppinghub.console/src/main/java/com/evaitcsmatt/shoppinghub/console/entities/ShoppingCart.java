package com.evaitcsmatt.shoppinghub.console.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {
	private List<CartItem> cart;
	
	public ShoppingCart() {
		cart = new ArrayList();
	}
	
	public ShoppingCart(List<CartItem> cart) {
		this.cart = cart;
	}
	
	public List<CartItem> getCart() {
		return cart;
	}

	public void setCart(List<CartItem> cart) {
		this.cart = cart;
	}

	public void addItem(Product product, int quantity) {
		for (CartItem item : cart) {
			if(item.getProduct().getId() == product.getId()) {
				System.out.println("Item already in cart adding extra");
				item.setQuantity(item.getQuantity() + quantity);
				return;
			}
		}
		cart.add(new CartItem(product, quantity));
	}
	
	public void removeItem(Product product, int quantity) {
		for (CartItem item : cart) {
			if(item.getProduct().getId() == product.getId()) {
				item.setQuantity(item.getQuantity() - quantity);
			}
		}
	}
	
	public double getTotal() {
		double total = 0.0;
		for(CartItem item: cart) {
			total += item.getProduct().getPrice() * item.getQuantity();
		}
		return total;
	}
	
	public void clear() {
		cart.clear();
	}
	
	public void displayCart() {
		for(CartItem item : cart) {
			System.out.println(item.toString());
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(cart);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		return Objects.equals(cart, other.cart);
	}

	@Override
	public String toString() {
		return "ShoppingCart [cart=" + cart + "]";
	}
}
