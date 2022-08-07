package com.tdd.booking;

public class BookingConflictException extends Exception {
    public BookingConflictException(String message) {
        super(message);
    }
}
