package com.tdd.booking;

public class InvalidBookingTimeException extends Exception {
    public InvalidBookingTimeException(String message) {
        super(message);
    }
}
