package com.evaitcsmatt.shoppinghub.console;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.evaitcsmatt.shoppinghub.console.controllers.MainController;
import com.evaitcsmatt.shoppinghub.console.entities.Product;
import com.evaitcsmatt.shoppinghub.console.repository.ProductRepository;
import com.evaitcsmatt.shoppinghub.console.repository.SimpleJpaConfig;
import com.evaitcsmatt.shoppinghub.console.service.PeusdoShopServiceImpl;
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
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(SimpleJpaConfig.class);
    	MainController controller = context.getBean(MainController.class);
    	controller.startUi();
    }
}
