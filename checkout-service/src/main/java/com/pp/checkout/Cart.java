package com.pp.checkout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Cart {
    private final Map<Long, Long> cartItems;
    private BigDecimal checkoutPrice;
    private BigDecimal cartTotals;

    public Cart(Map<Long, Long> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        StringBuilder cartItem = new StringBuilder();
        cartItem.append("{\n");
        for (Map.Entry<Long, Long> entry : cartItems.entrySet()) {
            cartItem.append("     Product Code : " + entry.getKey() + ", Product Quantity : " + entry.getValue() + "\n");
        }
        cartItem.append("}\n");
        return "\ncartItems=\n" + cartItem.toString() +
                "checkoutPrice=" + checkoutPrice + "\n" +
                "cartTotals=" + cartTotals + "\n";
    }
}
