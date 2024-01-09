package me.shubhamjain.currency.conversion.service;

import me.shubhamjain.currency.conversion.dto.CurrencyDTO;

import java.math.BigDecimal;

public interface CurrencyService {
    BigDecimal convert(CurrencyDTO currencyDTO);
}
