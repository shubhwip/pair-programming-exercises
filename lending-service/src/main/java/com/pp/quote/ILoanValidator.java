package com.pp.quote;

import com.pp.exception.QuoteNotPossibleException;

import java.math.BigDecimal;
import java.util.List;

public interface ILoanValidator<L> {
    boolean validateLoanProvision(List<L> lenders, BigDecimal loanAmount) throws QuoteNotPossibleException;
}
