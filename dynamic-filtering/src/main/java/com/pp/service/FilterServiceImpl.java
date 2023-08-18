package com.pp.service;

import com.filtering.model.Product;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilterServiceImpl implements FilterService<Product> {
    @Override
    public List<Product> findProductsByName(List<Product> products, String nameFilter) {
        Objects.requireNonNull(products, "products are null");
        return products
                .stream()
                .filter(p -> p.getName().equals(nameFilter))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductsByFilter(List<Product> products, String filterName, String filterValue) {
        Objects.requireNonNull(products, "products are null");
        return applyFilter(filterName, filterValue, products);
    }

    @Override
    public List<Product> findProductsByMultipleFilters(List<Product> products, Map<String, String> filters) {
        Objects.requireNonNull(products, "products are null");
        Objects.requireNonNull(filters, "filters map is null");
        for (Map.Entry<String, String> m : filters.entrySet()) {
            String filterName = m.getKey();
            String filterValue = m.getValue();
            products = applyFilter(filterName, filterValue, products);
        }
        return products;
    }


    private List<Product> applyFilter(String filterName, String filterValue, List<Product> products) {
        List<Product> filteredProducts = new ArrayList<>();
        String cap = filterName.substring(0, 1).toUpperCase() + filterName.substring(1);
        Method fieldGetter = null;
        try {
            fieldGetter = Product.class.getMethod("get" + cap);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        for (Product p : products) {
            try {
                if (fieldGetter.invoke(p).equals(filterValue)) {
                    filteredProducts.add(p);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return filteredProducts;
    }
}
