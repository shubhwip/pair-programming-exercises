package com.pp.blackjack.operation;

import com.pp.blackjack.deck.Deck;
import com.pp.blackjack.model.Dealer;
import com.pp.blackjack.model.Player;

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
