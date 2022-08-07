package com.tdd.booking;

import java.util.List;
import java.util.Objects;

public class BookingManagerImpl implements IBookingManager {

    List<Slot> slots;
    IBookingValidation bookingValidation;
    String bookingType;

    public BookingManagerImpl(String bookingType, List<Slot> slots, IBookingValidation bookingValidation) {
        this.bookingType = bookingType;
        this.bookingValidation = bookingValidation;
        this.slots = slots;
    }

    @Override
    public void bookTime(Slot slot) throws BookingConflictException, InvalidBookingTimeException {
        Objects.requireNonNull(slot);
        try {
            bookingValidation.validate(slot);
            int givenSlotStartTime = Integer.parseInt(slot.getStartDate().split(":")[0]);
            int givenSlotEndDate = Integer.parseInt(slot.getEndDate().split(":")[0]);
            for(Slot s : slots) {
                int slotStartTime = Integer.parseInt(s.getStartDate().split(":")[0]);
                int slotEndDate = Integer.parseInt(s.getEndDate().split(":")[0]);
                if(slotStartTime <= givenSlotStartTime && slotEndDate >= givenSlotEndDate)
                    throw new BookingConflictException("There is an existing booking already with the slot " + s);
            }
            slots.add(slot);
        } catch (InvalidBookingTimeException e) {
            throw e;
        }

    }

    @Override
    public List<Slot> bookings() {
        return slots;
    }
}
