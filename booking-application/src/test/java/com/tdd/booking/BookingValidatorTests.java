package com.tdd.booking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingValidatorTests {

    IBookingValidation bookingValidation;

    @BeforeEach
    public void setUp() {
        bookingValidation = new BookingValidation();
    }

    @Test
    public void givenValidTimeSlot_whenBookingValidationIsCalled_thenReturnsSuccessfulValidation() throws InvalidBookingTimeException {
        Slot slot = new Slot("1:00 PM", "3:00 PM");
        boolean validationResult = bookingValidation.validate(slot);
        Assertions.assertEquals(true, validationResult);
    }

    @Test
    public void givenInvalidTimeSlot_whenBookingValidationIsCalled_thenReturnsSuccessfulValidation() {
        Slot slot = new Slot("1:30 PM", "2:30 PM");
        Assertions.assertThrows(InvalidBookingTimeException.class, () -> bookingValidation.validate(slot));
    }
}
