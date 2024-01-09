package me.shubhamjain.currency.conversion.model;

import java.math.BigDecimal;
import java.util.Map;

public class CurrencyRate {
    BaseCurrency baseCurrency;
    Map<String, BigDecimal> currencyRates;
}
