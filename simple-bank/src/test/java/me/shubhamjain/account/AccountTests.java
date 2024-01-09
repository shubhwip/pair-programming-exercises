package me.shubhamjain.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTests {

    AccountService accountService;

    @BeforeEach
    public void setUp() {
        accountService = new AccountServiceImpl();
    }

    @Test
    public void givenAnAccount_whenAddAmountIscalled_shouldReturnUpdatedAccount() {
        accountService.deposit(500);
        Assertions.assertEquals(500, accountService.balance());
    }

    @Test
    public void givenAnAccount_whenDepositAmountAndDebitAmountIscalled_shouldReturnUpdatedAccount() {

        accountService.deposit(500);
        accountService.withdraw(300);
        System.out.println(accountService.printStatement());

        Assertions.assertEquals(200, accountService.balance());
    }

    @Test
    public void givenAnAccountWithZeroMoney_whenWithdrawAmountIscalled_shouldThrowInsufficientAmountException() {

        Assertions.assertThrows(InsufficientAmountException.class, () -> accountService.withdraw(500));
    }

}
