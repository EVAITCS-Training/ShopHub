package com.evaitcsmatt.shoppinghub.console.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a product in the store with a name, price, and available quantity.
 * This entity is persisted in the database.
 */
@Entity
public class Product implements Serializable {
    /**
     * Unique identifier for the product (auto-generated).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Name of the product.
     */
    private String name;
    /**
     * Price of the product.
     */
    private double price;
    /**
     * Available quantity of the product in stock.
     */
    private int quantity;

    /**
     * Default constructor for JPA.
     */
    public Product() {}

    /**
     * Constructs a product with a name and price. Quantity defaults to 0.
     * @param name the product name
     * @param price the product price
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }

    /**
     * Constructs a product with a name, price, and quantity.
     * @param name the product name
     * @param price the product price
     * @param quantity the available quantity
     */
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Gets the product ID.
     * @return the product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the product ID.
     * @param id the product ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the product name.
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     * @param name the product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the product price.
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     * @param price the product price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the available quantity of the product.
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the available quantity of the product.
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        // Compare by name (or ID if available)
        return Objects.equals(name, product.name);
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
    }
}
