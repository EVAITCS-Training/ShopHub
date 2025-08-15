package com.evaitcsmatt.shoppinghub.console.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspects {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspects.class);
	
	
	@Before("execution(* com.evaitcsmatt.shoppinghub.console.controllers.MainController.startUi(..))")
	public void logApplicationStart(JoinPoint joinPoint) {
		LOGGER.info("🚀 === SHOPPING HUB APPLICATION STARTED ===");
		LOGGER.info("👤 User session beginning...");
	}
	
	@After("execution(* com.evaitcsmatt.shoppinghub.console.service.*.addItemToCart(..))")
	public void logAddItemToCart(JoinPoint joinPoint) {
		LOGGER.info("🛒 User initiated 'Add Item to Cart' operation");
	}
}
