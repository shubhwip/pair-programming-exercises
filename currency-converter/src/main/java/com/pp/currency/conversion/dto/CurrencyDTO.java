package com.pp.currency.conversion.dto;

import java.math.BigDecimal;

public class CurrencyDTO {
    private String currencyFrom;

    public CurrencyDTO(String currencyFrom, BigDecimal amount, String currencyTo) {
        this.currencyFrom = currencyFrom;
        this.amount = amount;
        this.currencyTo = currencyTo;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    private BigDecimal amount;
    private String currencyTo;
}
