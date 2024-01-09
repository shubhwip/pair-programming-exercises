package me.shubhamjain.blackjack.manager;

public interface BlackJackManager {
    // Distribute Cards and Shuffle Cards
    void begin();

    // Updates Score and Find out if there is early winner
    void calculateScore();

    // Start Round
    void startRound();

    // Settle the Game
    void settle();

}
