package me.shubhamjain.blackjack.operation;

import me.shubhamjain.blackjack.model.Dealer;
import me.shubhamjain.blackjack.deck.Deck;
import me.shubhamjain.blackjack.model.Player;

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
