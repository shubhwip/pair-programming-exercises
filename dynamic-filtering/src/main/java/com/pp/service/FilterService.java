package com.filtering.service;

import java.util.List;
import java.util.Map;

public interface FilterService<T> {
    public List<T> findProductsByName(List<T> products, String nameFilter);

    public List<T> findProductsByFilter(List<T> products, String filterName, String filterValue);

    public List<T> findProductsByMultipleFilters(List<T> products, Map<String, String> filters);
}
