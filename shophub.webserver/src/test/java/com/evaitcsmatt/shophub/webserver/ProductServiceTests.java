package com.evaitcsmatt.shophub.webserver;

import com.evaitcsmatt.shophub.webserver.dtos.PostNewProduct;
import com.evaitcsmatt.shophub.webserver.entities.Product;
import com.evaitcsmatt.shophub.webserver.exceptions.ProductDuplitcationException;
import com.evaitcsmatt.shophub.webserver.repositories.ProductRepository;
import com.evaitcsmatt.shophub.webserver.services.ProductService;
import com.evaitcsmatt.shophub.webserver.utils.ProductMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductServiceTests {
    @Mock// No Implementation // @Spy uses Real Implementation
    private ProductRepository productRepository;

    @Spy
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void createProduct_ShouldSucceed_VerifyRepositoryHit() {
        PostNewProduct request = new PostNewProduct("Test", "Test Description", 15.00, 25L);
        Product product = new Product(0, "Test", "Test Description", 15.00, 25L, false, null, null, null, null, null);
        productService.createProduct(request);

        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    void createProduct_ShouldThrowException_DuplicateName() {
        PostNewProduct request = new PostNewProduct("Test", "Test Description", 15.00, 25L);
        Mockito.when(productRepository.existsByNameIgnoreCase(request.getName())).thenReturn(true);
        Throwable throwable = assertThrows(ProductDuplitcationException.class, () -> productService.createProduct(request));
        assertThat(throwable).isInstanceOf(ProductDuplitcationException.class);
    }
}
