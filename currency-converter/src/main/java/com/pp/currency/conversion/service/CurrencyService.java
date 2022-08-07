package com.pp.currency.conversion.service;

import com.pp.currency.conversion.dto.CurrencyDTO;

import java.math.BigDecimal;

public interface CurrencyService {
    BigDecimal convert(CurrencyDTO currencyDTO);
}
