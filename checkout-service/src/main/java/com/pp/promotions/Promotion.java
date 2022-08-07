package com.pp.promotions;

import com.pp.checkout.Cart;
import com.pp.product.Product;
import com.pp.product.Repository;

public abstract class Promotion {
    protected Promotion successor;

    public void SetSuccessor(Promotion successor) {
        this.successor = successor;
    }

    public abstract void apply(Cart cart, Repository<Product> repository);
}
