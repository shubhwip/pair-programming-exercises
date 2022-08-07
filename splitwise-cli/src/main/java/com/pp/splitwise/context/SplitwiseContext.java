package com.pp.splitwise.context;

import com.pp.splitwise.splitter.SplitTransaction;
import com.pp.splitwise.splitter.Splitter;

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
