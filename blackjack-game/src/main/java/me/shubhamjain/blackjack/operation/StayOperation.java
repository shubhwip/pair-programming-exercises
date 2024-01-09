package me.shubhamjain.blackjack.operation;

import me.shubhamjain.blackjack.model.Dealer;
import me.shubhamjain.blackjack.deck.Deck;
import me.shubhamjain.blackjack.model.Player;

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
