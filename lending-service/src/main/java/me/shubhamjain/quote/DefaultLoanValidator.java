package me.shubhamjain.quote;

import me.shubhamjain.exception.QuoteNotPossibleException;
import me.shubhamjain.model.Lender;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class DefaultLoanValidator implements ILoanValidator<Lender> {
    @Override
    public boolean validateLoanProvision(List<Lender> lenders, BigDecimal loanAmount) throws QuoteNotPossibleException {
        if (lenders == null || lenders.isEmpty()) {
            log.error("There are no Lenders available. It is not Possible to provide a quote");
            throw new QuoteNotPossibleException("There are no Lenders available. It is not Possible to provide a quote");
        }
        BigDecimal amount = lenders.stream().map(Lender::getAvailable).reduce(BigDecimal::add)
                .orElseThrow(() -> new QuoteNotPossibleException("Exception Occurred in calculating sum for Lenders available amount. It is not possible to provide a quote."));
        if ((loanAmount.compareTo(new BigDecimal((QuoteConstants.LOAN_LOWER_REQUEST_LIMIT))) < 0
                || loanAmount.compareTo(new BigDecimal((QuoteConstants.LOAN_UPPER_REQUEST_LIMIT))) > 0
                || !loanAmount.remainder(new BigDecimal((QuoteConstants.LOAN_REQUEST_MULTIPLES))).equals(BigDecimal.ZERO))) {
            log.error("A quote may be requested in any 100 increment between 1000 and 15000 inclusive. It is not possible to provide a quote.");
            throw new QuoteNotPossibleException("A quote may be requested in any 100 increment between 1000 and 15000 inclusive. It is not possible to provide a quote.");
        } else if (amount.compareTo(loanAmount) < 0) {
            log.error("There are no lenders available currently to serve requested loan amount. It is not possible to provide a quote.");
            throw new QuoteNotPossibleException("There are no lenders available currently to serve requested loan amount. It is not possible to provide a quote.");
        }
        return true;
    }
}
