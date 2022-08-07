package com.pp.splitwise.splitstrategy;

import com.pp.splitwise.splitter.Borrower;
import com.pp.splitwise.splitter.SplitTransaction;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Slf4j
public class PercentageSplit implements SplitStrategy {
    @Override
    public SplitTransaction split(BigDecimal amount, List<Borrower> borrowers) {
        Objects.requireNonNull(borrowers, "Borrowers List should not be null");
        Objects.requireNonNull(amount, "Amount to Split should not be null");

        BigDecimal sum = BigDecimal.ZERO;
        for(Borrower b: borrowers) {
            if(b.getPercentageSplit() == null || !b.getPercentageSplit().isPresent()) {
                log.error("Percentage Split Data Not Found for borrower {}", b);
                throw new InvalidSplitInputException("Percentage Split Data Not Found for borrower " + b);
            }
            sum = sum.add(b.getPercentageSplit().get());
        }

        if(sum.compareTo(BigDecimal.valueOf(100)) > 0) {
            log.error("Split data among borrowers {} sums to more or equal to 100 percent", borrowers);
            throw new InvalidSplitInputException("Split data among borrowers sums to more or equal to 100 percent");
        }

        for(Borrower b : borrowers) {
            b.setAmount(b.getPercentageSplit().get().multiply(amount).divide(BigDecimal.valueOf(100)));
        }

        return new SplitTransaction(borrowers.get(0).getLenderId(), borrowers, amount);
    }
}
