package com.pp.promotions;

import com.pp.checkout.Cart;
import com.pp.product.Product;
import com.pp.product.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class CartTotalPromotion extends Promotion {

    private BigDecimal cartTotals;
    private BigDecimal discountPercentage;

    @Override
    public void apply(Cart cart, Repository<Product> repository) {
        if (cart.getCheckoutPrice().compareTo(cartTotals) == 1) {
            log.info("CartTotalPromotion: Applying Cart Total Promotion with cart total excedding {} amount and discount {}", cartTotals, discountPercentage);
            BigDecimal discount = cart.getCheckoutPrice().multiply(
                    new BigDecimal(String.valueOf(discountPercentage.divide(new BigDecimal("100")))));
            cart.setCheckoutPrice(cart.getCheckoutPrice().subtract(discount).setScale(2, RoundingMode.HALF_EVEN));
            log.debug("CartTotalPromotion: Cart Checkout Price after applying cart total promotion {}", cart.getCheckoutPrice());
        } else {
            log.debug("CartTotalPromotion: Cart Total Promotion can not be applied as it cart total doesn't exceed {}", cartTotals);
        }
        if (successor != null) {
            log.debug("CartTotalPromotion: Applying another promotion after cart total promotions");
            successor.apply(cart, repository);
        }
    }
}