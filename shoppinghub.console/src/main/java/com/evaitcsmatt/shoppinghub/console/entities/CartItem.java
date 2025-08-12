package com.evaitcsmatt.shoppinghub.console.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents an item in the shopping cart, linking a product and its quantity.
 * This entity is persisted in the database.
 */
@Entity
public class CartItem {
    /**
     * Unique identifier for the cart item (auto-generated).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * The product associated with this cart item.
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    /**
     * The quantity of the product in the cart.
     */
    private int quantity;

    /**
     * Default constructor for JPA.
     */
    public CartItem() {}

    /**
     * Constructs a cart item with a product and quantity.
     * @param product the product
     * @param quantity the quantity
     */
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Gets the product for this cart item.
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product for this cart item.
     * @param product the product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets the quantity for this cart item.
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity for this cart item.
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CartItem other = (CartItem) obj;
        return id == other.id && Objects.equals(product, other.product) && quantity == other.quantity;
    }

    @Override
    public String toString() {
        return "CartItem [id=" + id + ", product=" + product + ", quantity=" + quantity + "]";
    }
}
