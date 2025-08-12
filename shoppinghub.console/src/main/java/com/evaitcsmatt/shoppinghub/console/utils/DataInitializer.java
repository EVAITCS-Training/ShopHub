package com.evaitcsmatt.shoppinghub.console.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import com.evaitcsmatt.shoppinghub.console.entities.Product;
import com.evaitcsmatt.shoppinghub.console.repository.ProductRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

/**
 * Component responsible for initializing the database with sample product data
 * when the application starts. This ensures the store has a variety of products
 * available for demonstration and testing purposes.
 */
@Component
@Transactional
public class DataInitializer {
    /**
     * Repository for persisting and retrieving products.
     */
    private final ProductRepository productRepository;

    /**
     * Constructs a DataInitializer with the given product repository.
     * @param productRepository the product repository
     */
    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Populates the database with a predefined list of products after bean construction.
     * This method is automatically called by Spring after the bean is initialized.
     */
    @PostConstruct
    public void initializeData() {
        List<Product> products = List.of(
            // Fresh Produce
            new Product("Red Apple", 0.79, 150),
            new Product("Green Apple", 0.75, 120),
            new Product("Granny Smith Apple", 0.82, 100),
            new Product("Orange", 1.25, 80),
            new Product("Banana", 0.65, 200),
            new Product("Strawberries", 3.99, 45),
            new Product("Blueberries", 4.50, 30),
            new Product("Grapes", 2.89, 60),
            new Product("Pineapple", 3.49, 25),
            new Product("Mango", 1.99, 40),
            new Product("Avocado", 1.49, 75),
            new Product("Lemon", 0.89, 90),
            new Product("Lime", 0.79, 85),

            // Vegetables
            new Product("Carrot", 0.99, 110),
            new Product("Broccoli", 2.49, 55),
            new Product("Spinach", 2.99, 40),
            new Product("Lettuce", 1.79, 65),
            new Product("Tomato", 2.19, 95),
            new Product("Cucumber", 1.39, 70),
            new Product("Bell Pepper", 1.89, 80),
            new Product("Onion", 1.29, 120),
            new Product("Garlic", 4.99, 35),
            new Product("Potato", 2.99, 200),
            new Product("Sweet Potato", 1.99, 85),

            // Dairy & Eggs
            new Product("Milk (1 Gallon)", 3.79, 50),
            new Product("Eggs (Dozen)", 2.49, 75),
            new Product("Butter", 4.29, 40),
            new Product("Cheese (Cheddar)", 5.99, 30),
            new Product("Yogurt", 1.19, 60),
            new Product("Cream Cheese", 2.99, 25),
            new Product("Sour Cream", 2.49, 35),

            // Meat & Seafood
            new Product("Chicken Breast", 8.99, 45),
            new Product("Ground Beef", 6.49, 55),
            new Product("Salmon Fillet", 12.99, 20),
            new Product("Bacon", 5.99, 40),
            new Product("Ham", 7.49, 25),
            new Product("Turkey Slices", 4.99, 30),

            // Pantry Staples
            new Product("Bread (White)", 2.29, 80),
            new Product("Bread (Wheat)", 2.49, 70),
            new Product("Rice (5lb)", 4.99, 45),
            new Product("Pasta", 1.29, 120),
            new Product("Olive Oil", 6.99, 25),
            new Product("Salt", 0.99, 100),
            new Product("Black Pepper", 3.49, 40),
            new Product("Sugar", 2.99, 60),
            new Product("Flour", 3.29, 50),
            new Product("Cereal (Cheerios)", 4.49, 35),
            new Product("Oatmeal", 3.99, 45),

            // Beverages
            new Product("Coffee (Ground)", 8.99, 40),
            new Product("Tea Bags", 3.49, 55),
            new Product("Orange Juice", 3.99, 30),
            new Product("Soda (Coke)", 1.99, 100),
            new Product("Water Bottle", 0.99, 200),
            new Product("Energy Drink", 2.49, 85),
            new Product("Beer (6-pack)", 7.99, 60),
            new Product("Wine", 12.99, 25),

            // Snacks & Sweets
            new Product("Chocolate Bar", 1.49, 150),
            new Product("Potato Chips", 2.99, 90),
            new Product("Pretzels", 2.49, 70),
            new Product("Cookies", 3.49, 80),
            new Product("Ice Cream", 4.99, 35),
            new Product("Candy (Gummy Bears)", 1.99, 120),
            new Product("Nuts (Mixed)", 5.99, 40),
            new Product("Granola Bar", 4.49, 65),

            // Household Items
            new Product("Toilet Paper (12-pack)", 8.99, 40),
            new Product("Paper Towels", 5.49, 55),
            new Product("Dish Soap", 2.99, 75),
            new Product("Laundry Detergent", 7.99, 30),
            new Product("Trash Bags", 6.49, 45),
            new Product("Light Bulb", 3.99, 60),
            new Product("Batteries (AA)", 4.99, 80),
            new Product("Aluminum Foil", 3.49, 50),

            // Personal Care
            new Product("Shampoo", 5.99, 45),
            new Product("Conditioner", 5.99, 40),
            new Product("Body Wash", 4.49, 55),
            new Product("Toothpaste", 2.99, 70),
            new Product("Toothbrush", 1.99, 90),
            new Product("Deodorant", 3.49, 65),
            new Product("Soap Bar", 1.49, 100),
            new Product("Lotion", 4.99, 50),

            // Electronics
            new Product("Phone Charger", 19.99, 25),
            new Product("Headphones", 29.99, 15),
            new Product("USB Cable", 9.99, 35),
            new Product("Power Bank", 24.99, 20),
            new Product("Bluetooth Speaker", 49.99, 12),
            new Product("Laptop Mouse", 15.99, 30),
            new Product("Keyboard", 39.99, 18),

            // Books & Media
            new Product("Novel (Fiction)", 12.99, 40),
            new Product("Cookbook", 19.99, 25),
            new Product("Magazine", 4.99, 60),
            new Product("DVD Movie", 9.99, 45),
            new Product("Board Game", 24.99, 20),
            new Product("Puzzle (1000pc)", 14.99, 30),

            // Clothing Basics
            new Product("T-Shirt", 9.99, 75),
            new Product("Jeans", 29.99, 40),
            new Product("Socks (3-pack)", 7.99, 85),
            new Product("Underwear (3-pack)", 12.99, 60),
            new Product("Baseball Cap", 14.99, 35),
            new Product("Hoodie", 24.99, 25),

            // Garden & Outdoor
            new Product("Plant (Pothos)", 8.99, 30),
            new Product("Garden Hose", 19.99, 15),
            new Product("Fertilizer", 12.99, 25),
            new Product("Watering Can", 9.99, 20),
            new Product("Garden Gloves", 5.99, 40),

            // Office Supplies
            new Product("Pen (Blue)", 1.29, 200),
            new Product("Pencil", 0.79, 150),
            new Product("Notebook", 3.99, 80),
            new Product("Stapler", 7.99, 25),
            new Product("Paper (500 sheets)", 8.99, 35),
            new Product("Folder", 2.49, 90),
            new Product("Highlighter", 2.99, 70),

            // Seasonal/Special Items
            new Product("Halloween Candy", 3.99, 100),
            new Product("Christmas Lights", 12.99, 30),
            new Product("Beach Towel", 19.99, 25),
            new Product("Sunscreen", 8.99, 45),
            new Product("Umbrella", 15.99, 20),

            // High-Value Items (for testing expensive purchases)
            new Product("Laptop Computer", 799.99, 5),
            new Product("Smart TV (55\")", 599.99, 3),
            new Product("Gaming Console", 499.99, 8),
            new Product("Smartphone", 699.99, 10),
            new Product("Tablet", 329.99, 12),
            new Product("Coffee Machine", 129.99, 15),
            new Product("Vacuum Cleaner", 179.99, 8),
            new Product("Air Fryer", 89.99, 20),

            // Zero/Low Stock Items (for testing out-of-stock scenarios)
            new Product("Limited Edition Item", 99.99, 0),
            new Product("Rare Collectible", 199.99, 1),
            new Product("Flash Sale Item", 49.99, 2),

            // Bulk Items
            new Product("Paper Plates (100ct)", 9.99, 25),
            new Product("Plastic Cups (50ct)", 5.99, 40),
            new Product("Napkins (200ct)", 3.99, 60),

            // International/Specialty Items
            new Product("Sushi Nori", 4.99, 15),
            new Product("Quinoa", 6.99, 30),
            new Product("Coconut Oil", 8.99, 25),
            new Product("Almond Milk", 3.49, 35),
            new Product("Protein Powder", 29.99, 20),
            new Product("Green Tea", 5.99, 40)
        );
        productRepository.saveAll(products);
        System.out.println("Data initialized with " + products.size() + " products");
    }
}
