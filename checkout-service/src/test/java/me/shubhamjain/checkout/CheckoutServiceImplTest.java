package me.shubhamjain.checkout;

import me.shubhamjain.product.Product;
import me.shubhamjain.product.Repository;
import me.shubhamjain.promotions.DefaultPromotionsRunner;
import me.shubhamjain.promotions.PromotionsRunner;
import me.shubhamjain.runner.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CheckoutServiceImplTest {
    Cart cart;
    Map<Long, Long> cartMap;
    CheckoutService checkoutService;
    PromotionsRunner promotionsRunner;
    Repository<Product> productRepository;
    Utils utils;

    @BeforeEach
    public void setUp() {
        utils = new Utils();
        cartMap = new HashMap<>();
        productRepository = utils.initializeData("src/test/resources/product.json");
        promotionsRunner = new DefaultPromotionsRunner(productRepository);
        checkoutService = new CheckoutServiceImpl(promotionsRunner, productRepository);
    }

    @Test
    public void givenProductCart_whenOnlyOnePromotionCanBeAppliedRelatedToCartSum_returnsDiscountedPrice() {
        // Arrange
        cartMap.put(1L, 1L);
        cartMap.put(2L, 1L);
        cartMap.put(3L, 1L);
        cart = new Cart(cartMap);
        // Act
        checkoutService.checkout(cart);
        // Assert
        Assertions.assertEquals(new BigDecimal("66.78"), cart.getCheckoutPrice());
    }

    @Test
    public void givenProductCart_whenOnlyOnePromotionCanBeAppliedRelatedToProductPromotion_returnsDiscountedPrice() {
        // Arrange
        cartMap.put(1L, 3L);
        cart = new Cart(cartMap);
        // Act
        checkoutService.checkout(cart);
        // Assert
        Assertions.assertEquals(new BigDecimal("25.50"), cart.getCheckoutPrice());
    }

    @Test
    public void givenProductCart_whenAllDefaultPromotionsRulesWillBeApplied_returnsDiscountedPrice() {
        // Arrange
        cartMap.put(1L, 2L);
        cartMap.put(2L, 2L);
        cart = new Cart(cartMap);
        // Act
        checkoutService.checkout(cart);
        // Assert
        Assertions.assertEquals(new BigDecimal("96.30"), cart.getCheckoutPrice());
    }

    @Test
    public void givenProductCart_whenNoneOfDefaultPromotionsWillBeApplied_returnsNoDiscountedPrice() {
        // Arrange
        cartMap.put(1L, 1L);
        cartMap.put(3L, 2L);
        cart = new Cart(cartMap);
        // Act
        checkoutService.checkout(cart);
        // Assert
        Assertions.assertEquals(new BigDecimal("49.15"), cart.getCheckoutPrice());
    }

}
