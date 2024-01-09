package me.shubhamjain.runner;

import com.google.gson.Gson;
import me.shubhamjain.checkout.Cart;
import me.shubhamjain.product.Product;
import me.shubhamjain.product.ProductRepository;
import me.shubhamjain.product.Repository;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class Utils {

    public Repository<Product> initializeData(String filename) {
        if (filename == null || filename.isEmpty()) {
            filename = "product.json";
        }
        Repository<Product> productRepository = new ProductRepository();
        // Learn more at https://stackoverflow.com/questions/26675048/classloader-getresource-doesnt-work-in-jar-file/26675158
        // This saved me : https://stackoverflow.com/questions/56382404/classloader-getresource-returning-null-while-using-file-getabsolutepath
        try (InputStream stream = new FileInputStream(filename)) {
            String result = new BufferedReader(new InputStreamReader(stream))
                    .lines().collect(Collectors.joining("\n"));
            Gson gson = new Gson();
            Product products[] = gson.fromJson(result, Product[].class);
            for (Product product : products) {
                productRepository.save(product);
            }
        } catch (Exception e) {
            log.error("Error occurred while loading product.json file {}", filename);
        }
        return productRepository;
    }

    public static BigDecimal getCartTotals(Cart cart, Repository<Product> productRepository) {
        BigDecimal currentTotals = BigDecimal.ZERO;
        for (Map.Entry<Long, Long> entry : cart.getCartItems().entrySet()) {
            currentTotals = currentTotals.add
                    (productRepository.findProductPrice
                            (entry.getKey()).multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return currentTotals;
    }

}
