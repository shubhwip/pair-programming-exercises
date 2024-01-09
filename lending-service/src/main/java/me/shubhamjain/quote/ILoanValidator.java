package me.shubhamjain.quote;

import me.shubhamjain.exception.QuoteNotPossibleException;

import java.math.BigDecimal;
import java.util.List;

public interface ILoanValidator<L> {
    boolean validateLoanProvision(List<L> lenders, BigDecimal loanAmount) throws QuoteNotPossibleException;
}
