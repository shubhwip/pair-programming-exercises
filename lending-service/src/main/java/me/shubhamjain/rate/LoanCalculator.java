package me.shubhamjain.rate;

import me.shubhamjain.quote.QuoteConstants;
import lombok.Getter;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import javax.money.MonetaryOperator;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

@Getter
public final class LoanCalculator implements MonetaryOperator {

    private final BigDecimal rate;
    private final int periods;


    private LoanCalculator(BigDecimal rate, int periods) {
        this.rate = Objects.requireNonNull(rate);
        if (periods < 0) {
            throw new IllegalArgumentException("Periods < 0");
        }
        this.periods = periods;
    }


    public static LoanCalculator of(BigDecimal rate, int periods) {
        return new LoanCalculator(rate, periods);
    }

    public static BigDecimal calculate(BigDecimal rate, int periods) {
        if (rate.equals(BigDecimal.ZERO) || periods == 0)
            return BigDecimal.ZERO;
        BigDecimal baseFactor = rate.divide(BigDecimal.valueOf(periods), MathContext.DECIMAL32)
                .add(BigDecimal.ONE);
        return baseFactor.pow(periods).subtract(BigDecimal.ONE);
    }

    public static MonetaryAmount calculate(MonetaryAmount amount, BigDecimal rate, int periods) {
        if (amount == null || rate.equals(BigDecimal.ZERO))
            return Money.of(0, QuoteConstants.CURRENCY);
        if (periods == 0) {
            return amount.getFactory().setNumber(0.0).create();
        }
        return amount.multiply(rate)
                .divide(
                        BigDecimal.ONE.subtract(
                                BigDecimal.ONE.divide((
                                        BigDecimal.ONE.add(rate)).pow(periods), MathContext.DECIMAL32), MathContext.DECIMAL32));
    }

    private static String toString(BigDecimal rate, int periods) {
        return "AnnualPercentageYield{" +
                "rate=" + rate +
                ", periods=" + periods +
                '}';
    }

    @Override
    public MonetaryAmount apply(MonetaryAmount amount) {
        return calculate(amount, rate, periods);
    }

    @Override
    public String toString() {
        return toString(this.rate, this.periods);
    }
}
