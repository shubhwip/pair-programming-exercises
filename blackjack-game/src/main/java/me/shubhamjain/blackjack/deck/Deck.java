package me.shubhamjain.blackjack.deck;

import me.shubhamjain.blackjack.model.Card;

public interface Deck {

    public void shuffleDeck();

    public boolean isShuffled();

    public Card take() throws EmptyDeckException;
}
