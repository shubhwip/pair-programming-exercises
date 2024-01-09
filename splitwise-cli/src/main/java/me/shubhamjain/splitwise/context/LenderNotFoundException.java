package me.shubhamjain.splitwise.context;

public class LenderNotFoundException extends RuntimeException {
    public LenderNotFoundException(String message) {
        super(message);
    }
}
