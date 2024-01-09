package me.shubhamjain.blackjack.deck;

// Todo : Should be checked exception
// Todo : Can be replaced with IllegalStateException
public class EmptyDeckException extends RuntimeException {
    // Todo: variable deck_is_empty doesn't conform to JLS specification
    public EmptyDeckException(String deck_is_empty) {
        super(deck_is_empty);
    }
}
