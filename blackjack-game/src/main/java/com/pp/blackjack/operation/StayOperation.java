package com.pp.blackjack.operation;

import com.pp.blackjack.model.Dealer;
import com.pp.blackjack.deck.Deck;
import com.pp.blackjack.model.Player;

public class StayOperation implements Operation {
    @Override
    public void apply(Player player, Deck deck) {
        return;
    }

    @Override
    public void apply(Dealer dealer, Deck deck) {
        return;
    }
}
