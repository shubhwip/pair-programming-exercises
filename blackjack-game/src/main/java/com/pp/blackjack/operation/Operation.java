package com.pp.blackjack.operation;

import com.pp.blackjack.model.Dealer;
import com.pp.blackjack.deck.Deck;
import com.pp.blackjack.model.Player;

public interface Operation {
    void apply(Player player, Deck deck);

    void apply(Dealer dealer, Deck deck);
}
