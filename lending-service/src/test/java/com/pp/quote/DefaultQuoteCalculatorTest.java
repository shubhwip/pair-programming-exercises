package com.pp.quote;

import com.pp.exception.QuoteNotPossibleException;
import com.pp.model.Lender;
import com.pp.model.Quote;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class DefaultQuoteCalculatorTest {
    ILoanValidator<Lender> loanValidator;
    IQuoteCalculator<Quote, Lender> quoteCalculator;
    List<Lender> lenders;

    @BeforeEach
    public void setup() {
        loanValidator = new DefaultLoanValidator();
        quoteCalculator = new DefaultQuoteCalculator(loanValidator);
        lenders = new ArrayList<>();
    }

    @Test
    public void givenListOfLendersAndGivenRequestedLendingAmount_WhenRequestedLendingAmountCanBeProvided_ReturnsQuote() throws QuoteNotPossibleException {
        // Arrange
        lenders.add(new Lender("Jane", new BigDecimal("0.069"), new BigDecimal("480")));
        lenders.add(new Lender("Fred", new BigDecimal("0.071"), new BigDecimal("520")));
        // Act
        Quote quote = quoteCalculator.getQuote(lenders, new BigDecimal("1000"));
        // Assert
        assertThat(new BigDecimal("1115.64"), Matchers.comparesEqualTo(quote.getTotalRepayment()));
    }

    @Test
    public void givenNullLendersAndGivenRequestedLendingAmount_WhenRequestedLendingAmountCanNotBeProvided_ReturnsQuoteNotPossibleException() {
        // Arrange and Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions
                .assertThrows(QuoteNotPossibleException.class, () -> quoteCalculator.getQuote(null, new BigDecimal("1000")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("There are no Lenders available. It is not Possible to provide a quote"));
    }

    @Test
    public void givenNullLendersAndGivenRequestedLendingAmountToZero_WhenRequestedLendingAmountCanBeProvided_ReturnsQuoteNotPossibleException() {
        // Arrange and Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions
                .assertThrows(QuoteNotPossibleException.class, () -> quoteCalculator.getQuote(null, new BigDecimal("1000")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("There are no Lenders available. It is not Possible to provide a quote"));
    }

    @Test
    public void givenListOfLendersAndGivenRequestedLendingAmountAsNegative_WhenRequestedLendingAmountCanNotBeProvided_ReturnsQuoteNotPossibleException() {
        // Arrange and Act
        lenders.add(new Lender("Jane", new BigDecimal("0.069"), new BigDecimal("480")));
        lenders.add(new Lender("Fred", new BigDecimal("0.071"), new BigDecimal("520")));
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions
                .assertThrows(QuoteNotPossibleException.class, () -> quoteCalculator.getQuote(lenders, new BigDecimal("-3")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("A quote may be requested in any 100 increment between 1000 and 15000 inclusive"));
    }

    @Test
    public void givenListOfLendersEmptyAndGivenRequestedLendingAmount_WhenRequestedLendingAmountCanNotBeProvided_ReturnsQuoteNotPossibleException() {
        // Arrange and Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions
                .assertThrows(QuoteNotPossibleException.class, () -> quoteCalculator.getQuote(lenders, new BigDecimal("1000")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("There are no Lenders available. It is not Possible to provide a quote"));
    }

}
