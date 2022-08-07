package com.pp.blackjack.manager;

import com.pp.blackjack.deck.Deck;
import com.pp.blackjack.model.Dealer;
import com.pp.blackjack.model.Player;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultBlackJackStrategy implements BlackJackStrategy {

    @Override
    public int findNextMovePlayer(Player p, Deck d) {
        return 0;
    }

    @Override
    public int findNextMoveDealer(Dealer p, Deck d) {
        return 0;
    }
}
