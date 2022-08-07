package com.pp;

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
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class LendingServiceComponentTests {

    IParser<Lender> parser;
    ILoanValidator<Lender> loanValidator;
    IQuoteCalculator<Quote, Lender> quoteCalculator;

    @BeforeEach
    public void setup() {
        parser = new CSVParser();
        loanValidator = new DefaultLoanValidator();
        quoteCalculator = new DefaultQuoteCalculator(loanValidator);
    }

    @Test
    public void givenLendersAndRequestedLoan_WhenLoanCanBeProvided_ReturnQuote() throws InputFileParseException, QuoteNotPossibleException {
        // Arrange
        List<Lender> lenders = parser.getLenders("src/test/resources/input/lenders-test1.csv");
        // Act
        Quote quote = quoteCalculator.getQuote(lenders, new BigDecimal("1000"));
        //log.info("Quote {}", quote.toString());
        // Assert
        assertThat(new BigDecimal("1115.64").setScale(2, BigDecimal.ROUND_HALF_EVEN), Matchers.comparesEqualTo(quote.getTotalRepayment()));
    }

    @Test
    public void givenLendersAndRequestedLoan_WhenLoanCanNotBeProvided_ThrowsQuoteNotPossibleException() throws InputFileParseException {
        // Act
        List<Lender> lenders = parser.getLenders("src/test/resources/input/lenders-test1.csv");
        // Assert
        assertThrows(QuoteNotPossibleException.class, () -> quoteCalculator.getQuote(lenders, new BigDecimal("1700")));
    }

    @Test
    public void givenBigLendersListAndRequestedLoan_WhenLoanCanBeProvided_ReturnsQuote() throws InputFileParseException, QuoteNotPossibleException {
        // Act
        List<Lender> lenders = parser.getLenders("src/test/resources/input/lenders-test2.csv");
        Quote quote = quoteCalculator.getQuote(lenders, new BigDecimal("1700"));
        //log.info("Quote {}", quote.toString());
        // Assert
        assertThat(new BigDecimal("1902.60").setScale(2, BigDecimal.ROUND_HALF_EVEN), Matchers.comparesEqualTo(quote.getTotalRepayment()));
    }


    // Negative Test cases

    @Test
    public void givenNullLendersAndGivenRequestedLendingAmount_WhenRequestedLendingAmountCanNotBeProvided_ReturnsQuoteNotPossibleException() {
        // Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions
                .assertThrows(QuoteNotPossibleException.class, () -> quoteCalculator.getQuote(null, new BigDecimal("1000")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("There are no Lenders available. It is not Possible to provide a quote"));
    }

    @Test
    public void givenNullLendersAndGivenRequestedLendingAmountToZero_WhenRequestedLendingAmountCanBeProvided_ReturnsQuoteNotPossibleException() {
        // Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions
                .assertThrows(QuoteNotPossibleException.class, () -> quoteCalculator.getQuote(null, BigDecimal.ZERO));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("There are no Lenders available. It is not Possible to provide a quote"));
    }

    @Test
    public void givenListOfLendersAndGivenRequestedLendingAmountAsNegative_WhenRequestedLendingAmountCanNotBeProvided_ReturnsQuoteNotPossibleException() throws InputFileParseException {
        // Act
        List<Lender> lenders = parser.getLenders("src/test/resources/input/lenders-test2.csv");
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions
                .assertThrows(QuoteNotPossibleException.class, () -> quoteCalculator.getQuote(lenders, new BigDecimal("-3")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("A quote may be requested in any 100 increment between 1000 and 15000 inclusive"));
    }

    @Test
    public void givenListOfLendersEmptyAndGivenRequestedLendingAmount_WhenRequestedLendingAmountCanNotBeProvided_ReturnsQuoteNotPossibleException() {
        // Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions
                .assertThrows(QuoteNotPossibleException.class, () -> quoteCalculator.getQuote(new ArrayList<>(), new BigDecimal("1000")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("There are no Lenders available. It is not Possible to provide a quote"));
    }

    @Test
    public void givenLendersAndRequestedLoanAndCalculateWeightedAverageOfLendingInterest_WhenLoanCanBeProvidedAnd_ReturnQuote() throws InputFileParseException, QuoteNotPossibleException {
        // Arrange
        List<Lender> lenders = parser.getLenders("src/test/resources/input/lenders-test1.csv");
        // Act
        Quote quote = quoteCalculator.getQuote(lenders, new BigDecimal("1000"));
        //log.info("Quote {}", quote.toString());
        // Assert
        assertThat(new BigDecimal("1115.57").setScale(2, BigDecimal.ROUND_HALF_EVEN), Matchers.comparesEqualTo(quote.getTotalRepayment()));
    }

}
