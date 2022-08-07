package com.pp.currency.conversion.runner;

import com.pp.currency.conversion.service.CurrencyService;
import com.pp.currency.conversion.service.CurrencyServiceImpl;
import com.pp.currency.conversion.dto.CurrencyDTO;

import java.math.BigDecimal;

public class Runner {
    public static void main(String[] args) {
        CurrencyService currencyService = new CurrencyServiceImpl();
        System.out.println(currencyService.convert(new CurrencyDTO("GBP", new BigDecimal("2000"), "INR")));
    }
}
