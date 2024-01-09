package me.shubhamjain.quote;

import me.shubhamjain.exception.QuoteNotPossibleException;
import me.shubhamjain.model.Lender;
import me.shubhamjain.model.Quote;

import java.math.BigDecimal;
import java.util.List;

public interface IQuoteCalculator<Q extends Quote, L extends Lender> {
    Q getQuote(List<L> lenders, BigDecimal loanAmount) throws QuoteNotPossibleException;

    List<L> selectLenders(List<L> lenders, BigDecimal loanAmount);
}
