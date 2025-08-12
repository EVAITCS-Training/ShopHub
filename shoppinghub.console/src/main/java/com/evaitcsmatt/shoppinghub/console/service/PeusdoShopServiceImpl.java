package com.evaitcsmatt.shoppinghub.console.service;

import org.springframework.stereotype.Service;

import com.evaitcsmatt.shoppinghub.console.entities.Product;
import com.evaitcsmatt.shoppinghub.console.entities.ShoppingCart;
import com.evaitcsmatt.shoppinghub.console.entities.Store;
import com.evaitcsmatt.shoppinghub.console.exceptions.ProductNotFoundException;
import com.evaitcsmatt.shoppinghub.console.repository.ProductRepository;

import jakarta.annotation.PostConstruct;

/**
 * Implementation of the ShopService interface providing business logic for managing
 * products, inventory, and shopping cart operations in the shop.
 */
@Service
public class PeusdoShopServiceImpl implements ShopService {
    /**
     * Repository for accessing product data from the database.
     */
    private ProductRepository productRepository;
    /**
     * Store instance for managing product inventory.
     */
    private Store store;
    /**
     * Shopping cart instance for user cart operations.
     */
    private ShoppingCart shoppingCart;
    /**
     * Flag indicating if the store inventory was updated.
     */
    private boolean didStoreUpdate;

    /**
     * Constructs a new PeusdoShopServiceImpl instance and initializes the shopping service components.
     *
     * @param productRepository the repository for accessing product data; must not be null
     */
    public PeusdoShopServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.store = new Store();
        this.shoppingCart = new ShoppingCart();
        this.didStoreUpdate = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItemToCart(Product product, int quantity) {
        if(!store.hasProduct(product)) {
            throw new ProductNotFoundException("Product with name of " + product.getName() + " not found!");
        }
        shoppingCart.addItem(product, quantity);
        store.getStoreInventory().merge(product, (0 - quantity), Integer::sum);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeItemFromCart(Product product, int quantity) {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addProductToInventory(Product product, double price, int quantity) {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeProductFromInventory(Product product) {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void completeOrder(ShoppingCart cart) {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearCart() {
        shoppingCart.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateQuantityInCart(Product product, int quantity) {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayShop() {
        store.displayProducts();
    }

    /**
     * Initializes the store inventory with all products from the repository.
     * Should be called after construction to populate the store.
     */
    public void initStore() {
        System.out.println("filling store");
        this.productRepository.findAll().forEach(item -> {
            this.store.addProduct(item, item.getQuantity());
        });
    }
}
