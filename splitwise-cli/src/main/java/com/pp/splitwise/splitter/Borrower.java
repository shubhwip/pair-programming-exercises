package com.pp.splitwise.splitter;

import lombok.*;

import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Borrower {
    Integer lenderId;
    Integer borrowerId;
    BigDecimal amount;
    Optional<BigDecimal> percentageSplit;
    Optional<BigDecimal> absoluteSplit;
}
