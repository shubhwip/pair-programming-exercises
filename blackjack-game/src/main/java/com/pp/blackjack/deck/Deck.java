package com.pp.blackjack.deck;

import com.pp.blackjack.model.Card;

public interface Deck {

    public void shuffleDeck();

    public boolean isShuffled();

    public Card take() throws EmptyDeckException;
}
