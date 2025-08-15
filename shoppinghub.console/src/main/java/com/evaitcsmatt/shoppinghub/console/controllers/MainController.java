package com.evaitcsmatt.shoppinghub.console.controllers;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.evaitcsmatt.shoppinghub.console.entities.Product;
import com.evaitcsmatt.shoppinghub.console.service.ShopService;

@Controller //Component, Repository, Service, RestController
public class MainController {
	
	private ShopService shopService;
	private Scanner scanner;

	public MainController(ShopService shopService, Scanner scanner) {
		this.shopService = shopService;
		this.scanner = scanner;
	}
	
	public void startUi() {
		while(true) {
			displayMenu();
			System.out.println("Please make an entry: ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				shopService.displayShop();
				break;
			}
			case 2: {
				shopService.displayCart();
				break;
			}
			case 3: {
				postNewCartItem();
				break;
			}
			case 6: {
				System.exit(0);
				
			}
			default:
				System.out.println("Hey select number 1-6");
			}
		}
	}
	
	public void postNewCartItem() {
		Product product = new Product();
		int quantity;
		scanner.nextLine();
		System.out.println("Please enter the name of the product: ");
		product.setName(scanner.nextLine());
		System.out.println("Please enter the amount wanted: ");
		quantity = scanner.nextInt();
		shopService.addItemToCart(product, quantity);
	}

	
	private void displayMenu() {
		System.out.println("""
				+------------------------+
				|    Welcome To ShopHub  |
				+------------------------+
				|Main Menu               |
				|1. Display Store        |
				|2. Display Cart         |
				|3. Add Item to Cart     |
				|4. Remove Item from Cart|
				|5. Complete Order       |
				|6. Exit                 |
				+------------------------+
				""");
	}
	
	
}
