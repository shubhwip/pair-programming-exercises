package me.shubhamjain.splitwise;

import me.shubhamjain.splitwise.splitter.Borrower;
import me.shubhamjain.splitwise.splitter.DefaultSplitter;
import me.shubhamjain.splitwise.splitter.Splitter;
import me.shubhamjain.splitwise.splitter.SplitTransaction;
import me.shubhamjain.splitwise.splitter.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

public class SplitwiseTwoUserSystemTests {

    User user1;
    User user2;
    Splitter runner;

    @BeforeEach
    public void setUp() {
        user1 = User.builder().userId(1).userName("Shubham").build();
        user2 = User.builder().userId(2).userName("Abhishek").build();
    }

    @Test
    public void givenUser1LendsMoneyToUser2_whenSplitIsEqual_shouldReturnSuccessfulSplit() {
        Borrower b = Borrower.builder().borrowerId(user2.getUserId()).lenderId(user1.getUserId()).build();
        runner = new DefaultSplitter(user1, Arrays.asList(b), BigDecimal.valueOf(100), "equal");

        SplitTransaction t = runner.splitAmount();

        for(Borrower user2 :t.getBorrowers()) {
            Assertions.assertEquals(BigDecimal.valueOf(50), user2.getAmount());
        }
    }

    @Test
    public void givenUser1LendsMoneyToUser2_whenSplitIsPercentage_shouldReturnSuccessfulSplit() {
        Borrower b = Borrower.builder().borrowerId(user2.getUserId()).lenderId(user1.getUserId())
                .percentageSplit(Optional.of(BigDecimal.valueOf(30))).build();

        runner = new DefaultSplitter(user1, Arrays.asList(b), BigDecimal.valueOf(100), "percentage");

        SplitTransaction t = runner.splitAmount();

        for(Borrower user2 :t.getBorrowers()) {
            Assertions.assertEquals(BigDecimal.valueOf(30), user2.getAmount());
        }
    }

    @Test
    public void givenUser1LendsMoneyToUser2_whenSplitIsAbsolute_shouldReturnSuccessfulSplit() {
        Borrower b = Borrower.builder().borrowerId(user2.getUserId()).lenderId(user1.getUserId())
                .absoluteSplit(Optional.of(BigDecimal.valueOf(40))).build();

        runner = new DefaultSplitter(user1, Arrays.asList(b), BigDecimal.valueOf(100), "absolute");

        SplitTransaction t = runner.splitAmount();

        for(Borrower user2 :t.getBorrowers()) {
            Assertions.assertEquals(BigDecimal.valueOf(40), user2.getAmount());
        }
    }


}
