package com.evaitcsmatt.shoppinghub.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.evaitcsmatt.shoppinghub.console.repository.SimpleJpaConfig;

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
    	
    	EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
    	EntityManager em = emf.createEntityManager();
    	
    	System.out.println(em.getProperties());
    }
}
