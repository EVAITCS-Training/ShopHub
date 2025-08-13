package com.evaitcsmatt.shoppinghub.console.service;

import com.evaitcsmatt.shoppinghub.console.entities.Product;
import com.evaitcsmatt.shoppinghub.console.entities.ShoppingCart;

/**
 * Service interface for shop operations such as managing products, cart, and orders.
 * Defines the contract for adding/removing products, handling the cart, and completing orders.
 */
public interface ShopService {
    /**
     * Adds a product to the shopping cart with the specified quantity.
     * @param product the product to add
     * @param quantity the quantity to add
     */
    void addItemToCart(Product product, int quantity);

    /**
     * Removes a quantity of a product from the shopping cart.
     * @param product the product to remove
     * @param quantity the quantity to remove
     */
    void removeItemFromCart(Product product, int quantity);

    /**
     * Adds a new product to the store inventory.
     * @param product the product to add
     * @param price the price of the product
     * @param quantity the quantity to add
     */
    void addProductToInventory(Product product, double price, int quantity);

    /**
     * Removes a product from the store inventory.
     * @param product the product to remove
     */
    void removeProductFromInventory(Product product);

    /**
     * Completes the order for the given shopping cart.
     * @param cart the shopping cart to complete the order for
     */
    void completeOrder();

    /**
     * Clears all items from the shopping cart.
     */
    void clearCart();

    /**
     * Updates the quantity of a product in the shopping cart.
     * @param product the product to update
     * @param quantity the new quantity
     */
    void updateQuantityInCart(Product product, int quantity);

    /**
     * Displays all products in the shop.
     */
    void displayShop();
    void displayCart();
}
