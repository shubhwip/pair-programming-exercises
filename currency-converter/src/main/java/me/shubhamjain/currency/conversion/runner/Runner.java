package me.shubhamjain.currency.conversion.runner;

import me.shubhamjain.currency.conversion.service.CurrencyService;
import me.shubhamjain.currency.conversion.service.CurrencyServiceImpl;
import me.shubhamjain.currency.conversion.dto.CurrencyDTO;

import java.math.BigDecimal;

public class Runner {
    public static void main(String[] args) {
        CurrencyService currencyService = new CurrencyServiceImpl();
        System.out.println(currencyService.convert(new CurrencyDTO("GBP", new BigDecimal("2000"), "INR")));
    }
}
