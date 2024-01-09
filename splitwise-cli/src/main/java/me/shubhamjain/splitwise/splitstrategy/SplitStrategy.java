package me.shubhamjain.splitwise.splitstrategy;

import me.shubhamjain.splitwise.splitter.Borrower;
import me.shubhamjain.splitwise.splitter.SplitTransaction;

import java.math.BigDecimal;
import java.util.List;

public interface SplitStrategy {
    SplitTransaction split(BigDecimal amount, List<Borrower> borrowers);
}
