package com.pp.runner;

import com.pp.checkout.Cart;
import com.pp.product.Product;
import com.pp.product.Repository;
import com.pp.product.exception.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class UtilsTest {

    Utils utils;

    @BeforeEach
    public void setUp() {
        utils = new Utils();
    }

    @Test
    public void testInitializeData_whenProductsFileIsPresent_returnsProductRepository() {
        // Arrange
        Repository<Product> productRepository = new Utils().initializeData("src/test/resources/product.json");
        // Act and Assert
        Assertions.assertEquals(new BigDecimal("19.95"), productRepository.findByProductCode(3).getProductPrice());
    }

    @Test
    public void testInitializeData_whenProductsFileIsNotPresent_returnsProductRepository() {
        // Arrange
        Repository<Product> productRepository = new Utils().initializeData("src/test/resources/product1.json");
        // Act and Assert
        Assertions.assertThrows(ProductNotFoundException.class, () -> productRepository.findProductPrice(3));
    }

    @Test
    public void testgetCartTotalTest_whenProductsFileIsPresent_returnsCartTotals() {
        // Arrange
        Map<Long, Long> cartMap = new HashMap<>();
        cartMap.put(1L, 1L);
        cartMap.put(2L, 1L);
        cartMap.put(3L, 1L);
        Cart cart = new Cart(cartMap);
        Repository<Product> productRepository = new Utils().initializeData("src/test/resources/product.json");
        // Act and Assert
        Assertions.assertEquals(new BigDecimal("74.20"), Utils.getCartTotals(cart, productRepository));
    }
}
