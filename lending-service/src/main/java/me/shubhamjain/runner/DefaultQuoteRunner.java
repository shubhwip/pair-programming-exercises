package me.shubhamjain.runner;

import me.shubhamjain.exception.InputFileParseException;
import me.shubhamjain.exception.QuoteNotPossibleException;
import me.shubhamjain.model.Lender;
import me.shubhamjain.model.Quote;
import me.shubhamjain.parser.CSVParser;
import me.shubhamjain.parser.IParser;
import me.shubhamjain.quote.DefaultLoanValidator;
import me.shubhamjain.quote.DefaultQuoteCalculator;
import me.shubhamjain.quote.ILoanValidator;
import me.shubhamjain.quote.IQuoteCalculator;
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
