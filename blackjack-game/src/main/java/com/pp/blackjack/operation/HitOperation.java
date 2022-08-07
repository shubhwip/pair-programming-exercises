package com.pp.blackjack.operation;

import com.pp.blackjack.model.Dealer;
import com.pp.blackjack.deck.Deck;
import com.pp.blackjack.model.Player;

public class HitOperation implements Operation {

    @Override
    public void apply(Player player, Deck deck) {
        player.getHand().add(deck.take());
    }

    @Override
    public void apply(Dealer dealer, Deck deck) {
        dealer.getHand().add(deck.take());
    }
}
