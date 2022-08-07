package com.pp.utility;

import com.pp.model.Lender;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Slf4j
public final class LenderHelper {

    public static BigDecimal getAverageRate(final List<Lender> lenders) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Lender lender : lenders) {
            log.debug("Lender Rate inside getAverageRate API, {}", lender.getRate());
            sum = sum.add(lender.getRate(), new MathContext(4));
        }
        return sum.divide(new BigDecimal(lenders.size()), BigDecimal.ROUND_HALF_EVEN);
    }

    // ((Rate of A * amount of A) + (Rate of B * amount of B)) / (amount of A + amount of B)

    public static BigDecimal getWeightedAverageRate(final List<Lender> lenders) {
        BigDecimal upperSum = BigDecimal.ZERO;
        // Denominator Sum
        BigDecimal denSum = BigDecimal.ZERO;
        for (Lender lender : lenders) {
            upperSum = upperSum.add(lender.getRate().multiply(lender.getAvailable()), new MathContext(4));
            denSum = denSum.add(lender.getAvailable());
        }
        return upperSum.divide(denSum, BigDecimal.ROUND_HALF_EVEN);
    }
}
