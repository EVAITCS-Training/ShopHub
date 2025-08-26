package com.evaitcsmatt.shophub.webserver.exceptions;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(String productNotFound) {
        super(productNotFound);
    }
}
