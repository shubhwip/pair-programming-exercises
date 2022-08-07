package com.tdd.booking;

public interface IBookingValidation {
    boolean validate(Slot slot) throws InvalidBookingTimeException;
}
