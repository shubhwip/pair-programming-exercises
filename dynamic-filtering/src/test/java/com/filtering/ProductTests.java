package com.filtering;

import me.shubhamjain.model.Product;
import com.filtering.service.FilterService;
import com.filtering.service.FilterServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductTests {

    FilterService filterService;
    Product p1;
    Product p2;
    Product p3;
    Product p4;
    List<Product> allProducts;


    @BeforeEach
    public void setUp() {
        filterService = new FilterServiceImpl();
        p1 = new Product("name1", "category1", 100L);
        p2 = new Product("name1", "category2", 200L);
        p3 = new Product("name1", "category1", 300L);
        p4 = new Product("name2", "category1", 300L);
        allProducts = Arrays.asList(p1, p2, p3, p4);
    }

    @Test
    public void shouldReturnAllProducts_WhenNameEqualsGivenValue() {

        List<Product> expectedProducts = Arrays.asList(p1, p2, p3);

        // When
        List<Product> products = filterService.findProductsByName(allProducts, "name1");

        // Then
        Assertions.assertEquals(expectedProducts, products);
    }


    @Test
    public void shouldReturnFilteredProducts_WhenFilterNameAndFilterValueIsGiven() {
        //Given
        List<Product> expectedProducts = Arrays.asList(p1, p2, p3);
        // When
        List<Product> products = filterService.findProductsByFilter(allProducts, "name", "name1");

        // Then
        Assertions.assertEquals(expectedProducts, products);


    }

    @Test
    public void shouldReturnFilteredProducts_WhenFilterMapIsGiven() {
        //Given
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("name", "name1");
        filterMap.put("category", "category1");

        List<Product> expectedProducts = Arrays.asList(p1, p3);
        // When
        List<Product> products = filterService.findProductsByMultipleFilters(allProducts, filterMap);

        // Then
        Assertions.assertEquals(expectedProducts, products);


    }

}
