package me.shubhamjain.promotions;

import me.shubhamjain.checkout.Cart;
import me.shubhamjain.product.Product;
import me.shubhamjain.product.Repository;

public abstract class Promotion {
    protected Promotion successor;

    public void SetSuccessor(Promotion successor) {
        this.successor = successor;
    }

    public abstract void apply(Cart cart, Repository<Product> repository);
}
