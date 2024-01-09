package me.shubhamjain.blackjack.runner;

import me.shubhamjain.blackjack.manager.BlackJackManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultGameRunner implements GameRunner {

    BlackJackManager blackJackManager;

    @Override
    public void start() {
        blackJackManager.begin();
        blackJackManager.calculateScore();
        blackJackManager.startRound();
        blackJackManager.settle();
    }
}
