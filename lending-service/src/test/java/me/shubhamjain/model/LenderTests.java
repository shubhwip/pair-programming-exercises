package me.shubhamjain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class LenderTests {
    @Test
    public void shouldReturnNPEException_whenCreatingANewLenderInstanceWithNullValues() {
        Assertions
                .assertThrows(NullPointerException.class,
                        () -> new Lender(null, null, new BigDecimal("480")));
    }
}
