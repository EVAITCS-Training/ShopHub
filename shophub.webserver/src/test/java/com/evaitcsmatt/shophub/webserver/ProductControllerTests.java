package com.evaitcsmatt.shophub.webserver;

import com.evaitcsmatt.shophub.webserver.dtos.PostNewProduct;
import com.evaitcsmatt.shophub.webserver.repositories.ProductRepository;
import com.evaitcsmatt.shophub.webserver.services.ProductService;
import com.evaitcsmatt.shophub.webserver.utils.ProductMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringJUnitConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTests {

    @LocalServerPort
    private int port;

    @MockitoSpyBean
    private ProductService productService;

    @MockitoBean
    private ProductRepository productRepository;

    @MockitoSpyBean
    private ProductMapper productMapper;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        RestAssured.port = port;
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    void getStoreAddProduct_ShouldSuccess_ReturnNoContent() {
        PostNewProduct product = new PostNewProduct("Test", "Test Product", 7.99, 100);

        given()
                .contentType(ContentType.JSON)
                .body(product)
                .post("/api/v1/product/add")
                .then()
                .statusCode(204);
    }

    @Test
    void getStoreAddProduct_DuplicateName_ThrowExceptionReturnBadRequest() {
        PostNewProduct product = new PostNewProduct("Test", "Test Product", 7.99, 100);

        Mockito.when(productRepository.existsByNameIgnoreCase(product.getName())).thenReturn(true);

        given()
                .contentType(ContentType.JSON)
                .body(product)
                .post("/api/v1/product/add")
                .then()
                .statusCode(400)
                .body("message", equalTo("Product with the name " + product.getName() + " already exists!"));
    }
}
