package me.shubhamjain.splitwise;

import me.shubhamjain.splitwise.splitter.Borrower;
import me.shubhamjain.splitwise.splitter.DefaultSplitter;
import me.shubhamjain.splitwise.splitstrategy.InvalidSplitInputException;
import me.shubhamjain.splitwise.splitter.Splitter;
import me.shubhamjain.splitwise.splitter.SplitTransaction;
import me.shubhamjain.splitwise.splitter.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

public class SplitwiseMultiUserSystemTests {
    User user1;
    User user2;
    User user3;
    User user4;
    Splitter runner;

    @BeforeEach
    public void setUp() {
        user1 = User.builder().userId(1).userName("Shubham").build();
        user2 = User.builder().userId(2).userName("Abhishek").build();
        user3 = User.builder().userId(3).userName("Kapil").build();
        user4 = User.builder().userId(4).userName("Niharica").build();
    }

    @Test
    public void givenAUserLendsMoneyToGroupOfBorrowers_whenSplitStrategyIsEqual_shouldReturnSuccessfulSplitAmongBorrowers() {
        // Arrange
        Borrower borrower1 = Borrower.builder().borrowerId(user2.getUserId()).lenderId(user1.getUserId()).build();
        Borrower borrower2 = Borrower.builder().borrowerId(user2.getUserId()).lenderId(user1.getUserId()).build();
        Borrower borrower3 = Borrower.builder().borrowerId(user2.getUserId()).lenderId(user1.getUserId()).build();
        runner = new DefaultSplitter(user1, Arrays.asList(borrower1,borrower2,borrower3), BigDecimal.valueOf(100), "equal");

        // Act
        SplitTransaction t = runner.splitAmount();

        // Assert
        for(Borrower borrower :t.getBorrowers()) {
            Assertions.assertEquals(BigDecimal.valueOf(25), borrower.getAmount());
        }
    }

    @Test
    public void givenAUserLendsMoneyToGroupOfBorrowers_whenSplitStrategyIsPercentage_shouldReturnSuccessfulSplitAmongBorrowers() {
        // Arrange
        Borrower borrower1 = Borrower.builder().borrowerId(user2.getUserId()).lenderId(user1.getUserId())
                .percentageSplit(Optional.of(BigDecimal.valueOf(20))).build();
        Borrower borrower2 = Borrower.builder().borrowerId(user3.getUserId()).lenderId(user1.getUserId())
                .percentageSplit(Optional.of(BigDecimal.valueOf(30))).build();
        Borrower borrower3 = Borrower.builder().borrowerId(user4.getUserId()).lenderId(user1.getUserId())
                .percentageSplit(Optional.of(BigDecimal.valueOf(40))).build();
        runner = new DefaultSplitter(user1, Arrays.asList(borrower1, borrower2, borrower3), BigDecimal.valueOf(100), "percentage");

        // Act
        SplitTransaction t = runner.splitAmount();

        // Assert
        Assertions.assertEquals(BigDecimal.valueOf(20), t.getBorrowers().get(0).getAmount());
        Assertions.assertEquals(BigDecimal.valueOf(30), t.getBorrowers().get(1).getAmount());
        Assertions.assertEquals(BigDecimal.valueOf(40), t.getBorrowers().get(2).getAmount());
    }

    @Test
    public void givenAUserLendsMoneyToGroupOfBorrowers_whenSplitStrategyIsAbsolute_shouldReturnSuccessfulSplitAmoungBorrowers() {
        // Arrange
        Borrower borrower1 = Borrower.builder().borrowerId(user2.getUserId()).lenderId(user1.getUserId())
                .absoluteSplit(Optional.of(BigDecimal.valueOf(20))).build();
        Borrower borrower2 = Borrower.builder().borrowerId(user3.getUserId()).lenderId(user1.getUserId())
                .absoluteSplit(Optional.of(BigDecimal.valueOf(30))).build();
        Borrower borrower3 = Borrower.builder().borrowerId(user4.getUserId()).lenderId(user1.getUserId())
                .absoluteSplit(Optional.of(BigDecimal.valueOf(30))).build();
        runner = new DefaultSplitter(user1, Arrays.asList(borrower1, borrower2, borrower3), BigDecimal.valueOf(100), "absolute");

        // Act
        SplitTransaction t = runner.splitAmount();

        // Assert
        Assertions.assertEquals(BigDecimal.valueOf(20), t.getBorrowers().get(0).getAmount());
        Assertions.assertEquals(BigDecimal.valueOf(30), t.getBorrowers().get(1).getAmount());
        Assertions.assertEquals(BigDecimal.valueOf(30), t.getBorrowers().get(2).getAmount());
    }

    @Test
    public void givenAUserLendsMoneyToGroupOfBorrowersUsingAbsoluteSplitStrategy_whenOneOfTheBorrowersDontHaveSplitAmountDefined_shouldThrowInvalidSplitDataFoundException() {
        // Arrange
        Borrower borrower1 = Borrower.builder().borrowerId(user2.getUserId()).lenderId(user1.getUserId())
                .absoluteSplit(Optional.of(BigDecimal.valueOf(20))).build();
        Borrower borrower2 = Borrower.builder().borrowerId(user3.getUserId()).lenderId(user1.getUserId()).build();
        Borrower borrower3 = Borrower.builder().borrowerId(user4.getUserId()).lenderId(user1.getUserId())
                .absoluteSplit(Optional.of(BigDecimal.valueOf(30))).build();
        runner = new DefaultSplitter(user1, Arrays.asList(borrower1, borrower2, borrower3), BigDecimal.valueOf(100), "absolute");

        // Assert
        Assertions.assertThrows(InvalidSplitInputException.class, () -> runner.splitAmount());
    }

    @Test
    public void givenAUserLendsMoneyToGroupOfBorrowersUsingPercentageStrategy_whenOneOfTheBorrowersDontHaveSplitAmountDefined_shouldThrowInvalidSplitDataFoundException() {
        // Arrange
        Borrower borrower1 = Borrower.builder().borrowerId(user2.getUserId()).lenderId(user1.getUserId())
                .percentageSplit(Optional.of(BigDecimal.valueOf(20))).build();
        Borrower borrower2 = Borrower.builder().borrowerId(user3.getUserId()).lenderId(user1.getUserId())
                .build();
        Borrower borrower3 = Borrower.builder().borrowerId(user4.getUserId()).lenderId(user1.getUserId())
                .percentageSplit(Optional.of(BigDecimal.valueOf(40))).build();
        runner = new DefaultSplitter(user1, Arrays.asList(borrower1, borrower2, borrower3), BigDecimal.valueOf(100), "percentage");

        // Assert
        Assertions.assertThrows(InvalidSplitInputException.class, () -> runner.splitAmount());
    }

    @Test
    public void givenAUserLendsMoneyToGroupOfBorrowersUsingPercentageStrategy_whenSplitPercentageAmountIsInvalid_shouldThrowInvalidSplitDataFoundException() {
        // Arrange
        Borrower borrower1 = Borrower.builder().borrowerId(user2.getUserId()).lenderId(user1.getUserId())
                .percentageSplit(Optional.of(BigDecimal.valueOf(20))).build();
        Borrower borrower2 = Borrower.builder().borrowerId(user3.getUserId()).lenderId(user1.getUserId())
                .percentageSplit(Optional.of(BigDecimal.valueOf(40))).build();
        Borrower borrower3 = Borrower.builder().borrowerId(user4.getUserId()).lenderId(user1.getUserId())
                .percentageSplit(Optional.of(BigDecimal.valueOf(50))).build();
        runner = new DefaultSplitter(user1, Arrays.asList(borrower1, borrower2, borrower3), BigDecimal.valueOf(100), "percentage");

        // Assert
        Assertions.assertThrows(InvalidSplitInputException.class, () -> runner.splitAmount());
    }

    @Test
    public void givenAUserLendsMoneyToGroupOfBorrowersUsingAbsoluteSplitStrategy_whenSplitAbsoluteAmountIsInvalid_shouldThrowInvalidSplitDataFoundException() {
        // Arrange
        Borrower borrower1 = Borrower.builder().borrowerId(user2.getUserId()).lenderId(user1.getUserId())
                .absoluteSplit(Optional.of(BigDecimal.valueOf(50))).build();
        Borrower borrower2 = Borrower.builder().borrowerId(user3.getUserId()).lenderId(user1.getUserId())
                .absoluteSplit(Optional.of(BigDecimal.valueOf(60))).build();
        Borrower borrower3 = Borrower.builder().borrowerId(user4.getUserId()).lenderId(user1.getUserId())
                .absoluteSplit(Optional.of(BigDecimal.valueOf(60))).build();
        runner = new DefaultSplitter(user1, Arrays.asList(borrower1, borrower2, borrower3), BigDecimal.valueOf(100), "absolute");

        // Assert
        Assertions.assertThrows(InvalidSplitInputException.class, () -> runner.splitAmount());
    }

}
