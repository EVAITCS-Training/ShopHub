package com.evaitcsmatt.shophub.webserver.utils;

import com.evaitcsmatt.shophub.webserver.dtos.PostNewProduct;
import com.evaitcsmatt.shophub.webserver.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product postNewProductToProduct(PostNewProduct product) {
        return Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
