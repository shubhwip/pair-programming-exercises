package me.shubhamjain.splitwise.splitstrategy;

import me.shubhamjain.splitwise.splitter.Borrower;
import me.shubhamjain.splitwise.splitter.SplitTransaction;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Slf4j
public class AbsoluteSplit implements SplitStrategy {

    public SplitTransaction split(BigDecimal amount, List<Borrower> borrowers) {
        Objects.requireNonNull(borrowers, "Borrowers List should not be null");
        Objects.requireNonNull(amount, "Amount to Split should not be null");

        BigDecimal sum = BigDecimal.ZERO;
        for(Borrower b: borrowers) {
            if(b.getAbsoluteSplit() == null || !b.getAbsoluteSplit().isPresent()) {
                log.error("Absolute Split Data Not Found for borrower {}", b);
                throw new InvalidSplitInputException("Absolute Split Data Not Found for borrower " + b);
            }
            sum = sum.add(b.getAbsoluteSplit().get());
        }

        if(sum.compareTo(amount) > 0) {
            log.error("Absolute Split values among borrowers {} sums more than amount to split", borrowers);
            throw new InvalidSplitInputException("Absolute Split values among borrowers sums more than amount to split ");
        }

        for(Borrower b : borrowers) {
            b.setAmount(b.getAbsoluteSplit().get());
        }

        return new SplitTransaction(borrowers.get(0).getLenderId(), borrowers, amount);
    }
}
