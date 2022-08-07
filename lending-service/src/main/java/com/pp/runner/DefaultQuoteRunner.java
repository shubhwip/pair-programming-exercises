package com.pp.runner;

import com.pp.exception.InputFileParseException;
import com.pp.exception.QuoteNotPossibleException;
import com.pp.model.Lender;
import com.pp.model.Quote;
import com.pp.parser.CSVParser;
import com.pp.parser.IParser;
import com.pp.quote.DefaultLoanValidator;
import com.pp.quote.DefaultQuoteCalculator;
import com.pp.quote.ILoanValidator;
import com.pp.quote.IQuoteCalculator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class DefaultQuoteRunner implements IQuoteRunner {

    private final String fileName;
    private final BigDecimal loanAmount;

    @Override
    public void run() {
        try {
            IParser<Lender> parser = new CSVParser();
            List<Lender> lenderList = parser.getLenders(fileName);
            ILoanValidator<Lender> loanValidator = new DefaultLoanValidator();
            IQuoteCalculator<Quote, Lender> quoteCalculator = new DefaultQuoteCalculator(loanValidator);
            Quote quote = quoteCalculator.getQuote(lenderList, loanAmount);
            log.info("{}", quote.toString());
        } catch (InputFileParseException e) {
            log.error("Error occurred in parsing file, {}", e.getMessage());
        } catch (QuoteNotPossibleException e) {
            log.error("QuoteNotPossibleException, {}", e.getMessage());
        }
    }
}
