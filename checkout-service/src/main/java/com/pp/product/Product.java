package com.pp.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class Product {
    private final Long productCode;
    private final String productName;
    private final BigDecimal productPrice;
}

