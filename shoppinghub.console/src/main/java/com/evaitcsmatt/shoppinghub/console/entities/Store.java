package com.evaitcsmatt.shoppinghub.console.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a store containing a collection of products and their available quantities.
 * Provides methods to add products, display inventory, and check product existence.
 */
public class Store {
    /**
     * Map of products to their available quantities in the store.
     */
    private Map<Product, Integer> products;

    /**
     * Constructs an empty store with no products.
     */
    public Store() {
        products = new HashMap<>();
    }

    /**
     * Constructs a store with a predefined product inventory.
     * @param products Map of products and their quantities
     */
    public Store(Map<Product, Integer> products) {
        this.products = products;
    }

    /**
     * Adds or updates a product in the store with the specified quantity.
     * @param product the product to add or update
     * @param quantity the quantity to set for the product
     */
    public void addProduct(Product product, int quantity) {
        products.put(product, quantity);
    }

    /**
     * Displays all products in the store with their name, price, and quantity.
     */
    public void displayProducts() {
        for (Product product : products.keySet()) {
            System.out.println(product.getName() + ", " + product.getPrice() + ", " + products.get(product));
        }
    }

    /**
     * Returns the store's inventory as a map of products to quantities.
     * @return the inventory map
     */
    public Map<Product, Integer> getStoreInventory() {
        return products;
    }

    /**
     * Checks if the store contains the specified product.
     * @param product the product to check
     * @return true if the product exists in the store, false otherwise
     */
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
