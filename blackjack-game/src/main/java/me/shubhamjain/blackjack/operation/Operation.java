package me.shubhamjain.blackjack.operation;

import me.shubhamjain.blackjack.model.Dealer;
import me.shubhamjain.blackjack.deck.Deck;
import me.shubhamjain.blackjack.model.Player;

public interface Operation {
    void apply(Player player, Deck deck);

    void apply(Dealer dealer, Deck deck);
}
