package com.pp.promotions;

import com.pp.checkout.Cart;
import com.pp.product.Product;
import com.pp.product.Repository;
import com.pp.product.exception.ProductNotFoundException;
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
public class ProductPromotion extends Promotion {

    private long itemCode;
    private long itemCount;
    private BigDecimal itemDiscountedPrice;

    @Override
    public void apply(Cart cart, Repository<Product> repository) {
        long ItemCountInCart = cart.getCartItems().get(itemCode);
        if (ItemCountInCart >= itemCount) {
            log.info("ProductPromotion: Applying Product Promotion");
            BigDecimal currentPrice = BigDecimal.ZERO;
            for (long productCode : cart.getCartItems().keySet()) {
                if (productCode == itemCode) {
                    log.info("ProductPromotion: Found price for itemcode {}", itemCode);
                    currentPrice = repository.findProductPrice(productCode);
                    break;
                }
            }
            if (currentPrice.compareTo(BigDecimal.ZERO) == 0) {
                log.error("ProductPromotion: Product Not found with itemCode, {}", itemCode);
                throw new ProductNotFoundException();
            }
            if (currentPrice.compareTo(itemDiscountedPrice) == 1 && cart.getCheckoutPrice() != null) {
                BigDecimal difference = currentPrice.subtract(itemDiscountedPrice);
                BigDecimal finalPrice = cart.getCheckoutPrice()
                        .subtract(difference.multiply(BigDecimal.valueOf(ItemCountInCart)));
                cart.setCheckoutPrice(finalPrice.setScale(2, RoundingMode.HALF_EVEN));
                log.debug("ProductPromotion: Cart Checkout Price after applying product promotion {}", cart.getCheckoutPrice());
            } else {
                log.debug("ProductPromotion: Promotion Can't be applied as current price is {} and discounted price is {}", currentPrice, itemDiscountedPrice);
            }
        }
        if (successor != null) {
            log.debug("ProductPromotion: Applying next promotion");
            successor.apply(cart, repository);
        }
    }
}
