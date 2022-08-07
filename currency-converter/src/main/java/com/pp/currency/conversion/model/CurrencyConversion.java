package com.pp.currency.conversion.model;

import java.math.BigDecimal;

public class CurrencyConversion {
    public BigDecimal getCurrency() {
        return currency;
    }

    public void setCurrency(BigDecimal currency) {
        this.currency = currency;
    }

    BigDecimal currency;
}
