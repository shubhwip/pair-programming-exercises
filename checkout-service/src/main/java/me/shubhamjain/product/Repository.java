package me.shubhamjain.product;

import java.math.BigDecimal;
import java.util.List;

public interface Repository<T> {
    void save(T t);

    List<T> findAll();

    T findByProductCode(long productCode);

    BigDecimal findProductPrice(long productCode);

}
