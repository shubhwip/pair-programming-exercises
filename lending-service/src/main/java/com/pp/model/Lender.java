package com.pp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Lender {
    @NonNull
    private String name;
    @NonNull
    private BigDecimal rate;
    @NonNull
    private BigDecimal available;
}
