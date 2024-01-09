package me.shubhamjain.product;

import me.shubhamjain.product.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductRepository implements Repository<Product> {

    private final List<Product> products = new ArrayList<>();

    @Override
    public void save(Product product) {
        log.debug("ProductRepository: Saving Product {}", product);
        products.add(product);
    }

    @Override
    public List<Product> findAll() {
        log.debug("ProductRepository: Listing all the products");
        return products;
    }

    @Override
    public BigDecimal findProductPrice(long productCode) {
        Product p = products.stream().
                filter(x -> x.getProductCode() == productCode)
                .findFirst().orElseThrow(ProductNotFoundException::new);
        log.debug("ProductRepository: Price for the product code {} is {}", productCode, p.getProductPrice());
        return p.getProductPrice();
    }

    @Override
    public Product findByProductCode(long productCode) {
        log.debug("ProductRepository: Returning product for a product code {}", productCode);
        return products.stream()
                .filter(x -> x.getProductCode() == productCode)
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
    }
}
