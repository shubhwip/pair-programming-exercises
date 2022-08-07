package com.pp.runner;

import com.pp.quote.QuoteConstants;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class Runner {

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            log.error("Please request some loan amount");
            throw new IllegalArgumentException("Please provide correct arguments");
        }
        BigDecimal loanAmount = BigDecimal.ZERO;
        String defaultLendersFilePath = QuoteConstants.DEFAULT_LENDERS_FILE_PATH;
        try {
            loanAmount = new BigDecimal(args[0]);
        } catch (Exception e) {
            log.error("Exception occurred in parsing arguments passed to program {}", e.getMessage());
        }
        IQuoteRunner quoteRunner = new DefaultQuoteRunner(defaultLendersFilePath, loanAmount);
        quoteRunner.run();
    }
}
