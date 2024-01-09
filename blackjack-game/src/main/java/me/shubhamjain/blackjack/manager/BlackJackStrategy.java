package me.shubhamjain.blackjack.manager;

import me.shubhamjain.blackjack.deck.Deck;
import me.shubhamjain.blackjack.model.Dealer;
import me.shubhamjain.blackjack.model.Player;

public interface BlackJackStrategy {
    int findNextMovePlayer(Player p, Deck d);

    int findNextMoveDealer(Dealer p, Deck d);
}
