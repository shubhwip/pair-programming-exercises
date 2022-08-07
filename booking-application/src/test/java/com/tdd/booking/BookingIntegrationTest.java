package com.tdd.booking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BookingIntegrationTest {

    IBookingManager bookingManager;
    IBookingValidation bookingValidation;

    @BeforeEach
    public void setUp() {
        bookingValidation = new BookingValidation();
        bookingManager = new BookingManagerImpl("classroom", new ArrayList<>(), bookingValidation);
    }

    @Test
    public void givenCorrectStartAndEndTimeForAMeeting_WhenBookTimeIsCalled_thenBookingARoomShouldBeSuccessful() throws BookingConflictException, InvalidBookingTimeException {
        // Arrange
        Slot slot = new Slot("4:00 PM", "5:00 PM");
        // Act
        bookingManager.bookTime(slot);
        // Assert
        Assertions.assertEquals(1, bookingManager.bookings().size());
    }

    @Test
    public void givenIncorrectStartAndEndTimeForAMeeting_WhenBookTimeIsCalled_thenBookingARoomShouldFail()  {
        // Arrange
        Slot slot = new Slot("4:30 PM", "5:30 PM");
        // Act and Assert
        Assertions.assertThrows(InvalidBookingTimeException.class, () -> bookingManager.bookTime(slot));
    }

    @Test
    public void givenThereIsAlreadyABookingInParticularTimeSlot_WhenBookTimeIsCalled_thenBookingARoomShouldFailDueToConflict() throws BookingConflictException, InvalidBookingTimeException {
        // Arrange
        Slot slot = new Slot("4:00 PM", "5:00 PM");
        bookingManager.bookTime(slot);

        // Act and Assert
        Assertions.assertThrows(BookingConflictException.class, () -> bookingManager.bookTime(slot));
    }

    @Test
    public void givenMultipleUniqueCorrectStartAndEndTimes_WhenBookTimeIsCalled_thenBookingARoomShouldReturnSuccess() throws BookingConflictException, InvalidBookingTimeException {
        // Arrange
        Slot slot1 = new Slot("4:00 PM", "5:00 PM");
        Slot slot2 = new Slot("5:00 PM", "6:00 PM");

        // Act
        bookingManager.bookTime(slot1);
        bookingManager.bookTime(slot2);

        // Assert
        Assertions.assertEquals(2, bookingManager.bookings().size());
        Assertions.assertEquals("4:00 PM", bookingManager.bookings().get(0).getStartDate());
    }

    // Corner Case
    @Test
    public void givenCorrectStartAndEndTimes_WhenBookingAlreadyExistsInThoseHours_thenBookingARoomShouldReturnFailure() throws BookingConflictException, InvalidBookingTimeException {
        // Arrange
        Slot slot1 = new Slot("3:00 PM", "6:00 PM");
        Slot slot2 = new Slot("4:00 PM", "5:00 PM");

        // Act
        bookingManager.bookTime(slot1);

        // Assert
        Assertions.assertThrows(BookingConflictException.class, ()->bookingManager.bookTime(slot2));
    }

}
