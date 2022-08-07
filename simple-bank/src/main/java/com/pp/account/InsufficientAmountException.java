package com.pp.account;

public class InsufficientAmountException extends Exception {
    public InsufficientAmountException(String message) {
        super(message);
    }
}
