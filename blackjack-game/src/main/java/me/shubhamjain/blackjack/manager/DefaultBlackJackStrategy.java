package me.shubhamjain.blackjack.manager;

import me.shubhamjain.blackjack.deck.Deck;
import me.shubhamjain.blackjack.model.Dealer;
import me.shubhamjain.blackjack.model.Player;
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
