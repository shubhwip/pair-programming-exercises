package me.shubhamjain.blackjack.operation;

import me.shubhamjain.blackjack.deck.Deck;
import me.shubhamjain.blackjack.model.Dealer;
import me.shubhamjain.blackjack.model.Player;

public class BustOperation implements Operation {
    @Override
    public void apply(Player player, Deck deck) {
        player.setActive(false);
        player.setBust(true);
    }

    @Override
    public void apply(Dealer dealer, Deck deck) {
        dealer.setActive(false);
        dealer.setBust(true);
    }
}
