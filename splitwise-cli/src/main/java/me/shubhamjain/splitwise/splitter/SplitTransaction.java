package me.shubhamjain.splitwise.splitter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class SplitTransaction {
    Integer lenderId;
    List<Borrower> borrowers;
    BigDecimal amount;
}
