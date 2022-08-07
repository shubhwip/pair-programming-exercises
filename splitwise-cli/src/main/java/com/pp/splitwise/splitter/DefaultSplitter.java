package com.pp.splitwise.splitter;

import com.pp.splitwise.splitstrategy.SplitStrategy;
import com.pp.splitwise.splitstrategy.InvalidSplitInputException;
import com.pp.splitwise.splitstrategy.SplitFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Slf4j
public class DefaultSplitter implements Splitter {

    private User lender;
    private List<Borrower> borrowers;
    private BigDecimal amount;
    private String splitType;

    @Override
    public SplitTransaction splitAmount() {
        if (amount.compareTo(BigDecimal.ZERO) < 0 || borrowers.size() == 0) {
            log.error("Either Amount {} is Invalid or Borrowers {}", amount, borrowers);
            throw new InvalidSplitInputException("Either Amount is Invalid or Borrowers");
        }
        SplitStrategy splitStrategy = SplitFactory.getInstance(this.getSplitType());
        return splitStrategy.split(amount, borrowers);
    }
}
