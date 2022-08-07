package com.pp.rate;


import com.pp.quote.QuoteConstants;
import org.hamcrest.Matchers;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

public class LoanCalculatorTest {

    @Test
    public void shouldReturnEffectiveInterestRate_GivenAvgInterestRateAndTimePeriod() {
        // Arrange and Act
        BigDecimal eir = LoanCalculator.calculate(new BigDecimal("0.072"), 36).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        // Assert
        //Assertions.assertEquals(new BigDecimal("0.08").setScale(2), eir);
        assertThat(new BigDecimal("0.07").setScale(2, BigDecimal.ROUND_HALF_EVEN), Matchers.comparesEqualTo(eir));
    }

    @Test
    public void shouldReturnEffectiveInterestRateAsZero_GivenAvgInterestRateAsZeroAndTimePeriod() {
        // Arrange and Act
        BigDecimal eir = LoanCalculator.calculate(BigDecimal.ZERO, 36).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        // Assert
        assertThat(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN), Matchers.comparesEqualTo(eir));
    }

    @Test
    public void shouldReturnEffectiveInterestRateAsZero_GivenAvgInterestRateAndTimePeriodAsZero() {
        // Arrange and Act
        BigDecimal eir = LoanCalculator.calculate(new BigDecimal("0.072"), 0).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        // Assert
        assertThat(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN), Matchers.comparesEqualTo(eir));
    }

    @Test
    public void shouldReturnMonthlyPayment_GivenInitialLoanAmountAndAvgInterestRateAndTimePeriod() {
        // Arrange and Act
        MonetaryAmount amount = LoanCalculator.calculate(Money.of(1000, QuoteConstants.CURRENCY),
                new BigDecimal("0.072"), 36);
        BigDecimal result = new BigDecimal(amount.getNumber().toString()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        // Assert
        assertThat(new BigDecimal("78.42").setScale(2, BigDecimal.ROUND_HALF_EVEN), Matchers.comparesEqualTo(result));
    }

    @Test
    public void shouldReturnMonthlyPaymentAsZero_GivenInitialLoanAmountAsNullAndAvgInterestRateAndTimePeriod() {
        // Arrange and Act
        MonetaryAmount amount = LoanCalculator.calculate(null,
                new BigDecimal("0.072"), 36);
        BigDecimal result = new BigDecimal(amount.getNumber().toString()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        // Assert
        assertThat(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN), Matchers.comparesEqualTo(result));
    }

    @Test
    public void shouldReturnMonthlyPaymentAsZero_GivenInitialLoanAmountAndAvgInterestRateAsZeroAndTimePeriod() {
        // Arrange and Act
        MonetaryAmount amount = LoanCalculator.calculate(null,
                BigDecimal.ZERO, 36);
        BigDecimal result = new BigDecimal(amount.getNumber().toString()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        // Assert
        assertThat(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN), Matchers.comparesEqualTo(result));
    }

    @Test
    public void shouldReturnMonthlyPaymentAsZero_GivenInitialLoanAmountAndAvgInterestAndTimePeriodAsZero() {
        // Arrange and Act
        MonetaryAmount amount = LoanCalculator.calculate(null,
                new BigDecimal("0.072"), 0);
        BigDecimal result = new BigDecimal(amount.getNumber().toString()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        // Assert
        assertThat(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN), Matchers.comparesEqualTo(result));
    }

}
