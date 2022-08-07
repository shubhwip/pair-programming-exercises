package com.pp.checkout;

import com.pp.product.Product;
import com.pp.product.Repository;
import com.pp.promotions.PromotionsRunner;
import com.pp.runner.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@AllArgsConstructor
@Slf4j
public class CheckoutServiceImpl implements CheckoutService {

    private PromotionsRunner promotionsRunner;

    private Repository<Product> productRepository;

    @Override
    public void checkout(Cart cart) {
        log.info("CheckoutServiceImpl: Checking out the cart");
        BigDecimal currentTotals = Utils.getCartTotals(cart, productRepository);
        log.debug("CheckoutServiceImpl: Setting the current checkout price {}", currentTotals);
        cart.setCheckoutPrice(currentTotals);
        cart.setCartTotals(currentTotals);
        log.debug("CheckoutServiceImpl: Setting the promotions");
        promotionsRunner.setPromotionsChain(cart);
    }
}
