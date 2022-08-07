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

/*
DefaultPromotionsRunner Test class sets two promotional rules as covered in the problem statement.
 */
public class DefaultPromotionsRunnerTest {
    Cart cart;
    Repository<Product> productRepository;
    Map<Long, Long> cartMap;
    PromotionsRunner promotionsRunner;
    Utils utils;

    @BeforeEach
    public void setUp() {
        utils = new Utils();
        productRepository = utils.initializeData("src/test/resources/product.json");
        cartMap = new HashMap<>();
        promotionsRunner = new DefaultPromotionsRunner(productRepository);
    }

    @Test
    public void givenProductCart_whenPromotionOnCartTotalCanBeAppliedOnly_returnsDiscountedPrice() {
        // Arrange
        cartMap.put(1L, 1L);
        cartMap.put(2L, 1L);
        cartMap.put(3L, 1L);
        cart = new Cart(cartMap);
        setCheckoutPrice(cart);
        // Act
        promotionsRunner.setPromotionsChain(cart);
        // Assert
        Assertions.assertEquals(new BigDecimal("66.78"), cart.getCheckoutPrice());
    }

    @Test
    public void givenProductCart_whenOnlyOnePromotionCanBeAppliedRelatedToProductPromotion_returnsDiscountedPrice() {
        // Arrange
        cartMap.put(1L, 3L);
        cart = new Cart(cartMap);
        setCheckoutPrice(cart);
        // Act
        promotionsRunner.setPromotionsChain(cart);
        // Assert
        Assertions.assertEquals(new BigDecimal("25.50"), cart.getCheckoutPrice());
    }

    @Test
    public void givenProductCart_whenAllDefaultPromotionsWillBeApplied_returnsDiscountedPrice() {
        // Arrange
        cartMap.put(1L, 2L);
        cartMap.put(2L, 2L);
        cart = new Cart(cartMap);
        setCheckoutPrice(cart);
        // Act
        promotionsRunner.setPromotionsChain(cart);
        // Assert
        Assertions.assertEquals(new BigDecimal("96.30"), cart.getCheckoutPrice());
    }

    @Test
    public void givenProductCart_whenNoneOfDefaultPromotionsWillBeApplied_returnsNoDiscountedPrice() {
        // Arrange
        cartMap.put(1L, 1L);
        cartMap.put(3L, 2L);
        cart = new Cart(cartMap);
        setCheckoutPrice(cart);
        // Act
        promotionsRunner.setPromotionsChain(cart);
        // Assert
        Assertions.assertEquals(new BigDecimal("49.15"), cart.getCheckoutPrice());
    }

    private void setCheckoutPrice(Cart cart) {
        BigDecimal currentTotals = Utils.getCartTotals(cart, productRepository);
        cart.setCheckoutPrice(currentTotals);
        cart.setCartTotals(currentTotals);
    }
}
