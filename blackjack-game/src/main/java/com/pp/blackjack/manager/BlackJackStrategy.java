package com.pp.blackjack.manager;

import com.pp.blackjack.deck.Deck;
import com.pp.blackjack.model.Dealer;
import com.pp.blackjack.model.Player;

public interface BlackJackStrategy {
    int findNextMovePlayer(Player p, Deck d);

    int findNextMoveDealer(Dealer p, Deck d);
}
