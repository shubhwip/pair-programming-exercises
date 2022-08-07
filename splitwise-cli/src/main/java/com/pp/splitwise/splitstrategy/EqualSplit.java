package com.pp.splitwise.splitstrategy;

import com.pp.splitwise.splitter.Borrower;
import com.pp.splitwise.splitter.SplitTransaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class EqualSplit implements SplitStrategy {

    @Override
    public SplitTransaction split(BigDecimal amount, List<Borrower> borrowers) {
        Objects.requireNonNull(borrowers, "Borrowers List should not be null");
        Objects.requireNonNull(amount, "Amount to Split should not be null");

        BigDecimal splitCount = BigDecimal.valueOf(borrowers.size() + 1);
        for(Borrower b : borrowers) {
            b.setAmount(amount.divide(splitCount));
        }
        return new SplitTransaction(borrowers.get(0).getLenderId(), borrowers, amount);
    }
}
