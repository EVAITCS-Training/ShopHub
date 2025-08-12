package com.evaitcsmatt.shoppinghub.console.service;

import org.springframework.stereotype.Service;

import com.evaitcsmatt.shoppinghub.console.entities.Product;
import com.evaitcsmatt.shoppinghub.console.entities.ShoppingCart;
import com.evaitcsmatt.shoppinghub.console.entities.Store;
import com.evaitcsmatt.shoppinghub.console.exceptions.ProductNotFoundException;
import com.evaitcsmatt.shoppinghub.console.repository.ProductRepository;

import jakarta.annotation.PostConstruct;

@Service
public class PeusdoShopServiceImpl implements ShopService {
	
	private ProductRepository productRepository;
	private Store store;
	private ShoppingCart shoppingCart;
	private boolean didStoreUpdate;
	
	/**
	 * Constructs a new PeusdoShopServiceImpl instance and initializes the shopping service components.
	 * <p>
	 * This constructor performs the following initialization steps:
	 * <ul>
	 *   <li>Injects the provided ProductRepository dependency</li>
	 *   <li>Creates a new Store instance for managing product inventory</li>
	 *   <li>Creates a new ShoppingCart instance for user cart operations</li>
	 *   <li>Initializes the store update flag to false</li>
	 *   <li>Populates the store with all existing products from the repository</li>
	 * </ul>
	 * 
	 * <p><strong>Note:</strong> This constructor eagerly loads all products from the repository
	 * into the store during initialization. For large product catalogs, consider implementing
	 * lazy loading or pagination to improve startup performance.
	 *
	 * @param productRepository the repository for accessing product data; must not be null
	 * @throws IllegalArgumentException if productRepository is null
	 * @throws RuntimeException if there's an error loading products from the repository
	 * 
	 * @see Store#addProduct(Object, int)
	 * @see ProductRepository#findAll()
	 * @since 1.0
	 */
	public PeusdoShopServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
		this.store = new Store();
		this.shoppingCart = new ShoppingCart();
		this.didStoreUpdate = false;
		
	}
	

	@Override
	public void addItemToCart(Product product, int quantity) {
		if(!store.hasProduct(product)) {
			throw new ProductNotFoundException("Product with name of " + product.getName() + " not found!");
		}
		shoppingCart.addItem(product, quantity);
		store.getStoreInventory().merge(product, (0 - quantity), Integer::sum);
	}

	@Override
	public void removeItemFromCart(Product product, int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProductToInventory(Product product, double price, int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProductFromInventory(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void completeOrder(ShoppingCart cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearCart() {
		shoppingCart.clear();
	}

	@Override
	public void updateQuantityInCart(Product product, int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayShop() {
		store.displayProducts();
	}
	
	
	public void initStore() {
		System.out.println("filling store");
		this.productRepository.findAll().forEach(item -> {
			this.store.addProduct(item, item.getQuantity());
		});
	}

}
