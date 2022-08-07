package com.pp.currency.conversion.service;

import com.pp.currency.conversion.model.CurrencyRate;

public interface CurrencyLoader {
    CurrencyRate loadCurrencyRates();
}
