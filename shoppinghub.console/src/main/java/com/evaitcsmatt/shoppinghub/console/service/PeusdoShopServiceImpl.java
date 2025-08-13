package com.evaitcsmatt.shoppinghub.console.service;

import java.awt.event.ItemEvent;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.evaitcsmatt.shoppinghub.console.entities.CartItem;
import com.evaitcsmatt.shoppinghub.console.entities.Order;
import com.evaitcsmatt.shoppinghub.console.entities.Product;
import com.evaitcsmatt.shoppinghub.console.entities.ShoppingCart;
import com.evaitcsmatt.shoppinghub.console.entities.Store;
import com.evaitcsmatt.shoppinghub.console.exceptions.ProductNotFoundException;
import com.evaitcsmatt.shoppinghub.console.repository.OrderRepository;
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
    private OrderRepository orderRepository;
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
    private volatile boolean didStoreUpdate;
    
    private ExecutorService executorService;

    /**
     * Constructs a new PeusdoShopServiceImpl instance and initializes the shopping service components.
     *
     * @param productRepository the repository for accessing product data; must not be null
     */
    public PeusdoShopServiceImpl(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.store = new Store();
        this.shoppingCart = new ShoppingCart();
        this.didStoreUpdate = false;
        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(autoSaveStore());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItemToCart(Product product, int quantity) {
        if(!store.hasProduct(product)) {
            throw new ProductNotFoundException("Product with name of " + product.getName() + " not found!");
        }
        Product oldProduct = store.getStoreInventory().keySet().stream()
        		.filter(item -> item.getName().equalsIgnoreCase(product.getName()))
        		.findFirst().get();
        shoppingCart.addItem(oldProduct, quantity);
        
        store.getStoreInventory().merge(product, (0 - quantity), Integer::sum);
        System.out.println("MJM:l70:addItemToCart: reading quantity");
        System.out.println(store.getStoreInventory().get(oldProduct));
        didStoreUpdate = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeItemFromCart(Product product, int quantity) {
    	 if(!store.hasProduct(product)){
             throw new ProductNotFoundException("Product with name of " + product.getName() + " not found!");
         }
         Optional<CartItem> item = shoppingCart.findItem(product);
         if(item.isPresent()){
             if(quantity > item.get().getQuantity()){
                 quantity = item.get().getQuantity();
             }
             shoppingCart.removeItem(product, quantity);
             store.getStoreInventory().merge(product, quantity, Integer::sum);
             didStoreUpdate = true;
         }
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
    public void completeOrder() {
        Order order = new Order();
        order.setItems(this.shoppingCart.getCart());
        order.setTotal(this.shoppingCart.getTotal());
        
        orderRepository.save(order);
        clearCart();
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
    	System.out.println("Store");
        store.displayProducts();
    }

    /**
     * Initializes the store inventory with all products from the repository.
     * Should be called after construction to populate the store.
     */
    @PostConstruct
    public void initStore() {
        System.out.println("filling store");
        this.productRepository.findAll().forEach(item -> {
            this.store.addProduct(item, item.getQuantity());
        });
    }

	@Override
	public void displayCart() {
		System.out.println("Shopping Cart");
		shoppingCart.displayCart();
	}
	
	private Runnable autoSaveStore() {
		return () -> {
			while(true) {
				if(didStoreUpdate) {
					System.out.println("Saving Store");
					for(Product product : store.getStoreInventory().keySet()) {
						product.setQuantity(store.getStoreInventory().get(product));
						productRepository.save(product);
					}
					System.out.println("Store Saved");
					didStoreUpdate = false;
				}								
			}
		};
	}
}
