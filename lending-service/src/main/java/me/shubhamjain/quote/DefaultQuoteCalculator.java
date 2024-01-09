package me.shubhamjain.quote;

import me.shubhamjain.exception.QuoteNotPossibleException;
import me.shubhamjain.model.Lender;
import me.shubhamjain.model.Quote;
import me.shubhamjain.rate.LoanCalculator;
import me.shubhamjain.utility.LenderHelper;
import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class DefaultQuoteCalculator implements IQuoteCalculator<Quote, Lender> {

    private final ILoanValidator<Lender> loanValidator;

    public DefaultQuoteCalculator(ILoanValidator<Lender> loanValidator) {
        this.loanValidator = loanValidator;
    }

    @Override
    public Quote getQuote(List<Lender> lenders, BigDecimal loanAmount) throws QuoteNotPossibleException {
        loanValidator.validateLoanProvision(lenders, loanAmount);
        List<Lender> selectedLenders = selectLenders(lenders, loanAmount);
        BigDecimal avgInterestRate = LenderHelper.getAverageRate(selectedLenders);
        log.debug("Average Interest Rate is {}", avgInterestRate);
        BigDecimal effectiveInterestRate = LoanCalculator.calculate(avgInterestRate, QuoteConstants.DEFAULT_LOAN_PERIOD);
        MonetaryAmount amount = LoanCalculator.calculate(Money.of(loanAmount, QuoteConstants.CURRENCY),
                effectiveInterestRate.divide(new BigDecimal(QuoteConstants.LOAN_AMORTIZATION_PERIOD), BigDecimal.ROUND_HALF_EVEN), QuoteConstants.DEFAULT_LOAN_PERIOD);
        BigDecimal monthlyAmount = new BigDecimal(amount.getNumber().toString()).setScale(QuoteConstants.DEFAULT_NUMBER_SCALE, BigDecimal.ROUND_HALF_EVEN);
        return new Quote(loanAmount,
                effectiveInterestRate.multiply(new BigDecimal("100")).setScale(QuoteConstants.DEFAULT_NUMBER_SCALE, BigDecimal.ROUND_HALF_EVEN),
                monthlyAmount,
                monthlyAmount.multiply(new BigDecimal(QuoteConstants.DEFAULT_LOAN_PERIOD)));
    }

    @Override
    public List<Lender> selectLenders(List<Lender> lenders, BigDecimal loanAmount) {
        // Sorting lenders from lowest to highest rates
        lenders.sort(Comparator.comparing(Lender::getRate));
        List<Lender> selectedLenders = new ArrayList<>(0);
        for (Lender lender : lenders) {
            if (loanAmount.compareTo(lender.getAvailable()) < 0) {
                selectedLenders.add(lender);
                break;
            } else {
                loanAmount = loanAmount.subtract(lender.getAvailable());
                selectedLenders.add(lender);
            }
        }
        return selectedLenders;
    }
}
