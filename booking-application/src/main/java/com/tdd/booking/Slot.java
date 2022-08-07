package com.tdd.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Slot {
    String startDate;
    String endDate;
}
