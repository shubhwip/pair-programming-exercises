package me.shubhamjain.currency.conversion.service;

import me.shubhamjain.currency.conversion.model.CurrencyRate;

public interface CurrencyLoader {
    CurrencyRate loadCurrencyRates();
}
