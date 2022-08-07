package com.tdd.booking;

import java.util.List;

public interface IBookingManager {
    void bookTime(Slot slot) throws BookingConflictException, InvalidBookingTimeException;

    List<Slot> bookings();
}
