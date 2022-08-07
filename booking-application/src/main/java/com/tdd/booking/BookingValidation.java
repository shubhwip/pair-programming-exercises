package com.tdd.booking;

public class BookingValidation implements IBookingValidation{
    @Override
    public boolean validate(Slot slot) throws InvalidBookingTimeException {
        boolean validSlot = true;
        String startTime = slot.getStartDate();
        String endTime = slot.getEndDate();
        String[] startTimeArray = startTime.split(":");
        if(startTimeArray.length != 2) {
            return false;
        } else {
            int hoursInStartTime = Integer.parseInt(startTimeArray[0]);
            if(hoursInStartTime < 1 || hoursInStartTime > 12) {
                validSlot = false;
            }
            String[] minutesAndTimeFormatInStartTime = startTimeArray[1].split("\\s");
            if(!minutesAndTimeFormatInStartTime[0].equals("00"))
                validSlot = false;
            else if(!(minutesAndTimeFormatInStartTime[1].equals("AM") || minutesAndTimeFormatInStartTime[1].equals("PM")))
                validSlot = false;
        }
        String[] endTimeArray = endTime.split(":");
        if(endTimeArray.length != 2) {
            validSlot = false;
        } else {
            int hoursInEndTime = Integer.parseInt(endTimeArray[0]);
            if(hoursInEndTime < 1 || hoursInEndTime > 12) {
                validSlot = false;
            }
            String[] minutesAndTimeFormatInEndTime = endTimeArray[1].split("\\s");
            if(!minutesAndTimeFormatInEndTime[0].equals("00"))
                validSlot = false;
            else if(!(minutesAndTimeFormatInEndTime[1].equals("AM") || minutesAndTimeFormatInEndTime[1].equals("PM")))
                validSlot = false;
        }
        if(!validSlot)
            throw new InvalidBookingTimeException("There is invalid time format in slot" + slot.toString() + "Please check the guidelines");
        return true;
    }
}
