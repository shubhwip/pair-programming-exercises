package me.shubhamjain.account;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Transaction {
    int amount;
    String date;
    int balance;
    AccountOperation accountOperation;
}
