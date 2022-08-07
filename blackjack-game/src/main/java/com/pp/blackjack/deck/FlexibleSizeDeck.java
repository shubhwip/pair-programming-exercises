package com.pp.blackjack.deck;

import com.pp.blackjack.model.Card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
/*
This Deck can be used in variations of the many blackjack games where more than 1 deck is required.
 */
public class FlexibleSizeDeck implements Deck {
    private List<Card> deck;

    // This variable is introduced to test shuffling of deck cards.
    private boolean isShuffled;

    public FlexibleSizeDeck(int size) {
        // This part is hardcoded dependency.
        // Use DI here
        deck = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            for (String suit : DeckConstants.SUITS) {
                for (String rank : DeckConstants.RANKS) {
                    deck.add(new Card(rank, suit));
                }
            }
        }
        isShuffled = false;
    }

    public void shuffleDeck() {
        // This part is hardcoded dependency
        // Use DI here
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
