package com.pp.splitwise.splitstrategy;

import com.pp.splitwise.splitter.Borrower;
import com.pp.splitwise.splitter.SplitTransaction;

import java.math.BigDecimal;
import java.util.List;

public interface SplitStrategy {
    SplitTransaction split(BigDecimal amount, List<Borrower> borrowers);
}
