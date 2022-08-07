package com.pp.promotions;

import com.pp.checkout.Cart;
import com.pp.product.Product;
import com.pp.product.Repository;
import com.pp.runner.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductPromotionTest {
    Cart cart;
    Repository<Product> productRepository;
    Promotion promotion;
    Map<Long, Long> cartMap;
    Utils utils;

    @BeforeEach
    public void setUp() {
        utils = new Utils();
        productRepository = utils.initializeData("src/test/resources/product.json");
        cartMap = new HashMap<>();
    }

    @Test
    public void givenProductCartWithThreeDifferentProducts_whenPromotionIsOnProductCodeOne_returnsNoDiscountedPrice() {
        // Arrange
        cartMap.put(1L, 1L);
        cartMap.put(2L, 1L);
        cartMap.put(3L, 1L);
        cart = new Cart(cartMap);
        setCheckoutPrice(cart);
        promotion = new ProductPromotion(1, 2, new BigDecimal("8.5"));
        // Act
        promotion.apply(cart, productRepository);
        // Assert
        Assertions.assertEquals(new BigDecimal("74.20"), cart.getCheckoutPrice());
    }

    @Test
    public void givenProductCartWithTwoSameProducts_whenPromotionIsOnProductCodeOne_returnsDiscountedPrice() {
        // Arrange
        cartMap.put(1L, 2L);
        cartMap.put(2L, 1L);
        cart = new Cart(cartMap);
        setCheckoutPrice(cart);
        promotion = new ProductPromotion(1, 2, new BigDecimal("8.5"));
        // Act
        promotion.apply(cart, productRepository);
        // Assert
        Assertions.assertEquals(new BigDecimal("62.00"), cart.getCheckoutPrice());
    }

    @Test
    public void givenProductCartWithTwoSameProductsInDifferentOrder_whenPromotionIsOnProductCodeOne_returnsDiscountedPrice() {
        // Arrange
        cartMap.put(2L, 1L);
        cartMap.put(1L, 2L);
        cart = new Cart(cartMap);
        setCheckoutPrice(cart);
        promotion = new ProductPromotion(1, 2, new BigDecimal("8.5"));
        // Act
        promotion.apply(cart, productRepository);
        // Assert
        Assertions.assertEquals(new BigDecimal("62.00"), cart.getCheckoutPrice());
    }

    @Test
    public void givenProductCartWithThreeSameProducts_whenPromotionIsOnProductCodeOne_returnsDiscountedPrice() {
        // Arrange
        cartMap.put(1L, 3L);
        cart = new Cart(cartMap);
        setCheckoutPrice(cart);
        promotion = new ProductPromotion(1, 2, new BigDecimal("8.5"));
        // Act
        promotion.apply(cart, productRepository);
        // Assert
        Assertions.assertEquals(new BigDecimal("25.50"), cart.getCheckoutPrice());
    }

    private void setCheckoutPrice(Cart cart) {
        BigDecimal currentTotals = Utils.getCartTotals(cart, productRepository);
        cart.setCheckoutPrice(currentTotals);
        cart.setCartTotals(currentTotals);
    }

}
