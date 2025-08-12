package com.evaitcsmatt.shoppinghub.console;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.evaitcsmatt.shoppinghub.console.entities.Product;
import com.evaitcsmatt.shoppinghub.console.repository.ProductRepository;
import com.evaitcsmatt.shoppinghub.console.repository.SimpleJpaConfig;
import com.evaitcsmatt.shoppinghub.console.service.ShopService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App
{
	//private static Map<String, Object> simpleIocContainer = new HashMap<>();
	
    public static void main( String[] args )
    {
//    	simpleIocContainer.put("userInputScanner", new Scanner(System.in));
//    	
//    	int num =  ((Scanner) simpleIocContainer.get("userInputScanner"))
//    			.nextInt();
//        System.out.println( "Hello World!" );
    	ApplicationContext context = new AnnotationConfigApplicationContext(SimpleJpaConfig.class);
    	ProductRepository productRepo = context.getBean(ProductRepository.class);
    	EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
    	EntityManager em = emf.createEntityManager();
    	
    	Product redApple = new Product("Red Apple", 0.79);
    	Product oragne = new Product("Orange", 1.25);
    	Product greenApple = new Product("Granny Smith Apple", 0.79);
    	productRepo.saveAll(List.of(redApple, oragne, greenApple));
    	ShopService service = context.getBean(ShopService.class);
    	service.displayShop();
    	service.addItemToCart(greenApple, 3);
    	
    	System.out.println(productRepo.findByNameLike("%Apple%"));
    }
}
