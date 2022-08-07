package com.pp.quote;

import com.pp.exception.QuoteNotPossibleException;
import com.pp.model.Lender;
import com.pp.parser.CSVParser;
import com.pp.parser.IParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * QuoteValidatorTest covers test coverage from Parser to QuoteValidation
 */
public class DefaultLoanValidatorTest {

    ILoanValidator<Lender> loanValidator;
    IParser<Lender> parser;
    List<Lender> lenders;

    @BeforeEach
    public void setup() {
        loanValidator = new DefaultLoanValidator();
        parser = new CSVParser();
        lenders = new ArrayList<>();
        lenders.add(new Lender("Jane", new BigDecimal("0.069"), new BigDecimal("480")));
        lenders.add(new Lender("Fred", new BigDecimal("0.071"), new BigDecimal("520")));
    }

    @Test
    public void givenListOfLendersAndGivenRequestedLendingAmount_WhenRequestedLendingAmountCanBeProvided_ReturnsTrue() throws QuoteNotPossibleException {
        // Arrange and Act
        boolean result = loanValidator.validateLoanProvision(lenders, new BigDecimal("1000"));
        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    public void givenListOfLendersAndGivenRequestedLendingAmount_WhenRequestedLendingAmountCantBeProvided_ThrowsQuoteNotPossibleException() {
        // Arrange and Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions.assertThrows(QuoteNotPossibleException.class,
                () -> loanValidator.validateLoanProvision(lenders, new BigDecimal("1700")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("There are no lenders available currently to serve requested loan amount"));
    }

    @Test
    public void givenListOfLendersAndGivenRequestedLendingAmount_WhenRequestedLendingAmountIsBelowTheRequiredLimits_ThrowsQuoteNotPossibleException() {
        // Arrange and Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions.assertThrows(QuoteNotPossibleException.class,
                () -> loanValidator.validateLoanProvision(lenders, new BigDecimal("991")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("A quote may be requested in any 100 increment between 1000 and 15000 inclusive"));
    }

    @Test
    public void givenListOfLendersAndGivenRequestedLendingAmount_WhenRequestedLendingAmountIsBelowTheRequiredLimitsAndMultipleOf100_ThrowsQuoteNotPossibleException() {
        // Arrange and Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions.assertThrows(QuoteNotPossibleException.class,
                () -> loanValidator.validateLoanProvision(lenders, new BigDecimal("900")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("A quote may be requested in any 100 increment between 1000 and 15000 inclusive"));
    }

    @Test
    public void givenListOfLendersAndGivenRequestedLendingAmount_WhenRequestedLendingAmountIsAboveTheRequiredLimits_ThrowsQuoteNotPossibleException() {
        // Arrange and Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions.assertThrows(QuoteNotPossibleException.class,
                () -> loanValidator.validateLoanProvision(lenders, new BigDecimal("16111")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("A quote may be requested in any 100 increment between 1000 and 15000 inclusive"));
    }

    // Added Negative test cases as to what can fail.

    @Test
    public void givenLendersAsNullAndGivenRequestedLendingAmount_WhenRequestedLendingAmountIsInTheLimits_ThrowsQuoteNotPossibleException() {
        // Arrange and Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions.assertThrows(QuoteNotPossibleException.class,
                () -> loanValidator.validateLoanProvision(null, new BigDecimal("1000")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("There are no Lenders available"));
    }

    @Test
    public void givenLendersAsNullAndGivenRequestedLendingAmountAsZero_WhenRequestedLendingAmountIsNotInTheRequiredLimits_ThrowsQuoteNotPossibleException() {
        // Arrange and Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions.assertThrows(QuoteNotPossibleException.class,
                () -> loanValidator.validateLoanProvision(null, BigDecimal.ZERO));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("There are no Lenders available"));
    }

    @Test
    public void givenLendersAndGivenRequestedLendingAmountAsNegative_WhenRequestedLendingAmountIsNotInTheRequiredLimits_ThrowsQuoteNotPossibleException() {
        // Arrange and Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions.assertThrows(QuoteNotPossibleException.class,
                () -> loanValidator.validateLoanProvision(lenders, new BigDecimal("-3")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("A quote may be requested in any 100 increment between 1000 and 15000 inclusive"));
    }

    @Test
    public void givenLendersAsEmptyAndGivenRequestedLendingAmount_WhenRequestedLendingAmountIsInTheRequiredLimits_ThrowsQuoteNotPossibleException() {
        // Arrange and Act
        QuoteNotPossibleException quoteNotPossibleExceptionThrown = Assertions.assertThrows(QuoteNotPossibleException.class,
                () -> loanValidator.validateLoanProvision(new ArrayList<>(), new BigDecimal("1000")));
        // Assert
        Assertions.assertTrue(quoteNotPossibleExceptionThrown.getMessage().contains("There are no Lenders available"));
    }
}
