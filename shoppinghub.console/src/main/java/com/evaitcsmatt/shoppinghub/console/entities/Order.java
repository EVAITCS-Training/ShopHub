package com.evaitcsmatt.shoppinghub.console.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a customer order containing a list of cart items and the total price.
 * This entity is persisted in the database.
 */
@Entity
@Table(name = "sh_orders")
public class Order {
    /**
     * Unique identifier for the order (auto-generated).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The list of items in the order.
     */
    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private List<CartItem> items;

    /**
     * The total price of the order.
     */
    private double total;

    /**
     * Default constructor for JPA.
     */
    public Order() {}

    /**
     * Constructs an order from a shopping cart.
     * @param cart the shopping cart to convert to an order
     */
    public Order(ShoppingCart cart) {
        this.items = cart.getCart();
        this.total = cart.getTotal();
    }

    /**
     * Gets the order ID.
     * @return the order ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the order ID.
     * @param id the order ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the list of items in the order.
     * @return the list of cart items
     */
    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Sets the list of items in the order.
     * @param items the list of cart items
     */
    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    /**
     * Gets the total price of the order.
     * @return the total price
     */
    public double getTotal() {
        return total;
    }

    /**
     * Sets the total price of the order.
     * @param total the total price
     */
    public void setTotal(double total) {
        this.total = total;
    }
}
