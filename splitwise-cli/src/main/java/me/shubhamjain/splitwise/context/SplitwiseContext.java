package me.shubhamjain.splitwise.context;

import me.shubhamjain.splitwise.splitter.SplitTransaction;
import me.shubhamjain.splitwise.splitter.Splitter;

import java.util.List;

public class SplitwiseContext {

    List<SplitTransaction> transactions;
    Splitter splitter;

    public SplitwiseContext(Splitter splitter) {
        this.splitter = splitter;
    }

    public void transact() {
        SplitTransaction splitTransaction = splitter.splitAmount();
        transactions.add(splitTransaction);
    }

}
