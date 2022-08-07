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

public class CartTotalPromotionTest {

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
    public void givenProductCartWithThreeDifferentProducts_whenPromotionIsOnCartTotalExceeding60_returnsDiscountedPrice() {
        // Arrange
        cartMap.put(1L, 1L);
        cartMap.put(2L, 1L);
        cartMap.put(3L, 1L);
        cart = new Cart(cartMap);
        setCheckoutPrice(cart);
        promotion = new CartTotalPromotion(new BigDecimal("60"), new BigDecimal("10"));
        // Act
        promotion.apply(cart, productRepository);
        // Assert
        Assertions.assertEquals(new BigDecimal("66.78"), cart.getCheckoutPrice());
    }

    @Test
    public void givenProductCartWithThreeProducts_whenPromotionIsOnCartTotalNotExceeding60_returnsNoDiscountedPrice() {
        // Arrange
        cartMap.put(1L, 3L);
        cart = new Cart(cartMap);
        setCheckoutPrice(cart);
        promotion = new CartTotalPromotion(new BigDecimal("60"), new BigDecimal("10"));
        // Act
        promotion.apply(cart, productRepository);
        // Assert
        Assertions.assertEquals(new BigDecimal("27.75"), cart.getCheckoutPrice());
    }

    private void setCheckoutPrice(Cart cart) {
        BigDecimal currentTotals = Utils.getCartTotals(cart, productRepository);
        cart.setCheckoutPrice(currentTotals);
        cart.setCartTotals(currentTotals);
    }
}
