package me.shubhamjain.product;

import me.shubhamjain.product.exception.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ProductRepositoryTest {

    Repository<Product> productRepository;
    Product product1;
    Product product2;

    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepository();
        product1 = new Product(1L, "Carry Bag", new BigDecimal("6.65"));
        product2 = new Product(2L, "Ramp Bag", new BigDecimal("6.85"));
        productRepository.save(product1);
        productRepository.save(product2);
    }

    @Test
    public void shouldSaveTheProduct() {
        Product product = new Product(1L, "Carry Bag", new BigDecimal("6.65"));
        productRepository.save(product);
        // Saved Two Products in Setup Method already
        Assertions.assertEquals(3, productRepository.findAll().size());
    }


    @Test
    public void shouldReturnAllTheProducts() {
        Assertions.assertEquals(2, productRepository.findAll().size());
    }

    @Test
    public void givenProductsInRepository_returnPricesBasedOnproductId() {
        Assertions.assertEquals(new BigDecimal("6.65"), productRepository.findProductPrice(1));
    }

    @Test
    public void givenProductsInRepository_returnProductBasedOnproductId() {
        Assertions.assertEquals("Carry Bag", productRepository.findByProductCode(1).getProductName());
    }


    @Test
    public void givenProductsInRepository_whenCalledProductBasedOnProductId_ProductCodeIsNotInRepository_ThrowProductNotFoundException() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> productRepository.findByProductCode(3));
    }

    @Test
    public void givenProductsInRepository_whenCalledProductPriceBasedOnProductId_whenProductCodeIsNotInRepository_ThrowProductNotFoundException() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> productRepository.findProductPrice(3));
    }

}
