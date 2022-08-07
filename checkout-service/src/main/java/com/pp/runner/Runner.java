package com.pp.runner;

import com.pp.checkout.Cart;
import com.pp.checkout.CheckoutServiceImpl;
import com.pp.product.Product;
import com.pp.product.Repository;
import com.pp.promotions.DefaultPromotionsRunner;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Runner {

    public static void main(String[] args) {
        // Required Data
        String filename = System.getenv("PRODUCT_DATA_LOCATION");
        if (filename == null || filename.isEmpty()) {
            log.info("Please set PRODUCT_DATA_LOCATION environment variable and set the absolute location of product.json file");
            System.exit(1);
        }
        Repository<Product> productRepository = new Utils().initializeData(filename);
        if (productRepository.findAll().size() == 0) {
            log.info("Please feed some data in product.json file");
            System.exit(1);
        }
        // Input
        Map<Long, Long> map = new HashMap<>();
        try {
            long[] array = Arrays.stream(args).mapToLong(Long::parseLong).toArray();
            for (int i = 0; i < array.length; i++) {
                map.merge(array[i], 1L, Long::sum);
            }
        } catch (Exception e) {
            log.error("Error occurred while reading cart items as input");
            log.error("Please make sure that cart items are passed with product code");
            throw e;
        }
        // Running the checkout system
        Cart cart = new Cart(map);
        CheckoutServiceImpl checkoutService = new CheckoutServiceImpl(new DefaultPromotionsRunner(productRepository), productRepository);
        checkoutService.checkout(cart);
        log.info("-----------------Your Cart is {} ", cart.toString());
        log.info("----------------- Checkout Price is {}", cart.getCheckoutPrice());
    }
}
