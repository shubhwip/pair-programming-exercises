package me.shubhamjain.account;

public interface AccountService {
    void deposit(int depositAmount);

    void withdraw(int withdrawAmount);

    String printStatement();

    int balance();

}
