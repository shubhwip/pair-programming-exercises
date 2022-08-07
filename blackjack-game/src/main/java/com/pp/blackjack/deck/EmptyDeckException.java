package com.pp.blackjack.deck;

public class EmptyDeckException extends RuntimeException {
    public EmptyDeckException(String deck_is_empty) {
        super(deck_is_empty);
    }
}
