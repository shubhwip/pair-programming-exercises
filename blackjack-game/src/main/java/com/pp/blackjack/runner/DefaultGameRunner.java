package com.pp.blackjack.runner;

import com.pp.blackjack.manager.BlackJackManager;
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
