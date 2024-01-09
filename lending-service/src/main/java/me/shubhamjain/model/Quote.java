package me.shubhamjain.model;

import me.shubhamjain.quote.QuoteConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Quote {
    @NonNull
    private BigDecimal loanAmount;
    @NonNull
    private BigDecimal annualInterestRate;
    @NonNull
    private BigDecimal monthlyRepayment;
    @NonNull
    private BigDecimal totalRepayment;

    @Override
    public String toString() {
        return "\nRequested Amount: " + loanAmount + " " + QuoteConstants.CURRENCY + "\n" +
                "Annual Interest Rate: " + annualInterestRate + "%\n" +
                QuoteConstants.LOAN_COMPOUNDING_TYPE + " Repayment: " + monthlyRepayment + " " + QuoteConstants.CURRENCY + "\n" +
                "Total Repayment: " + totalRepayment + " " + QuoteConstants.CURRENCY;
    }
}
