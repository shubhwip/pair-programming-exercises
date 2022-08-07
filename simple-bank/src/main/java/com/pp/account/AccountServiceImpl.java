package com.pp.account;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class AccountServiceImpl implements AccountService {

    List<Transaction> transactions;

    Integer balance;

    public AccountServiceImpl() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    @Override
    public void deposit(int depositAmount) {
        balance += depositAmount;
        Transaction deposit = new Transaction(depositAmount, new Date().toString(), balance, AccountOperation.DEPOSIT);
        transactions.add(deposit);
    }

    @Override
    public void withdraw(int withdrawAmount) {
        balance -= withdrawAmount;
        Transaction deposit = new Transaction(withdrawAmount, new Date().toString(), balance, AccountOperation.WITHDRAW);
        transactions.add(deposit);
    }

    @Override
    public String printStatement() {
        StringBuilder s = new StringBuilder();
        for(Transaction t: transactions) {
            s.append(t.toString());
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public int balance() {
        return balance;
    }
}
