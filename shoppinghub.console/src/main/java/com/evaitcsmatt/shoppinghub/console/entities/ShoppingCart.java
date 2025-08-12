package com.evaitcsmatt.shoppinghub.console.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a shopping cart containing a list of cart items.
 * Provides methods to add, remove, and display items, as well as calculate the total price.
 */
public class ShoppingCart {
    /**
     * The list of items in the shopping cart.
     */
    private List<CartItem> cart;

    /**
     * Constructs an empty shopping cart.
     */
    public ShoppingCart() {
        cart = new ArrayList();
    }

    /**
     * Constructs a shopping cart with a predefined list of cart items.
     * @param cart the list of cart items
     */
    public ShoppingCart(List<CartItem> cart) {
        this.cart = cart;
    }

    /**
     * Gets the list of cart items.
     * @return the list of cart items
     */
    public List<CartItem> getCart() {
        return cart;
    }

    /**
     * Sets the list of cart items.
     * @param cart the list of cart items
     */
    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    /**
     * Adds a product to the cart or increases its quantity if already present.
     * @param product the product to add
     * @param quantity the quantity to add
     */
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

    /**
     * Removes a quantity of a product from the cart.
     * @param product the product to remove
     * @param quantity the quantity to remove
     */
    public void removeItem(Product product, int quantity) {
        for (CartItem item : cart) {
            if(item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() - quantity);
            }
        }
    }

    /**
     * Calculates the total price of all items in the cart.
     * @return the total price
     */
    public double getTotal() {
        double total = 0.0;
        for(CartItem item: cart) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    /**
     * Clears all items from the cart.
     */
    public void clear() {
        cart.clear();
    }

    /**
     * Displays all items in the cart.
     */
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
