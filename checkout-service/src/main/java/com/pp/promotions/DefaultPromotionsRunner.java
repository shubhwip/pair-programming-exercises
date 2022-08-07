package com.pp.promotions;

import com.pp.checkout.Cart;
import com.pp.product.Product;
import com.pp.product.Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@AllArgsConstructor
@Slf4j
public class DefaultPromotionsRunner implements PromotionsRunner {

    private Repository<Product> repository;

    @Override
    public void setPromotionsChain(Cart cart) {
        log.info("DefaultPromotionsRunner: Setting Promotions Chain and Applying promotions");
        Promotion p1 = new ProductPromotion(1, 2, new BigDecimal("8.5"));
        Promotion p2 = new CartTotalPromotion(new BigDecimal("60"), new BigDecimal("10"));
        p1.SetSuccessor(p2);

        p1.apply(cart, repository);
    }
}
