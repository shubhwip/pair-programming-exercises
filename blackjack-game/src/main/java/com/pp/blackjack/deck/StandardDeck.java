package com.pp.blackjack.deck;

import com.pp.blackjack.model.Card;
import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Getter
public final class StandardDeck implements Deck {
    private List<Card> deck;
    private boolean isShuffled;

    public StandardDeck() {
        // This part is hardcoded dependency.
        deck = new LinkedList<>();
        for (String suit : DeckConstants.SUITS) {
            for (String rank : DeckConstants.RANKS) {
                deck.add(new Card(rank, suit));
            }
        }
        isShuffled = false;
    }

    public void shuffleDeck() {
        // This part is hardcoded dependency
        Collections.shuffle(deck);
        isShuffled = true;
    }

    public boolean isShuffled() {
        return isShuffled;
    }

    @Override
    public Card take() {
        if (deck.size() > 0)
            return deck.remove(0);
        throw new EmptyDeckException("Deck is Empty");
    }
}
