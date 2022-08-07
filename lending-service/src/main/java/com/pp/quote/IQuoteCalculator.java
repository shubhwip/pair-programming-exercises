package com.pp.quote;

import com.pp.exception.QuoteNotPossibleException;
import com.pp.model.Lender;
import com.pp.model.Quote;

import java.math.BigDecimal;
import java.util.List;

public interface IQuoteCalculator<Q extends Quote, L extends Lender> {
    Q getQuote(List<L> lenders, BigDecimal loanAmount) throws QuoteNotPossibleException;

    List<L> selectLenders(List<L> lenders, BigDecimal loanAmount);
}
