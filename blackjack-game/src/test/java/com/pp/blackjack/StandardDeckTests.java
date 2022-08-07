package com.pp.blackjack;

import com.pp.blackjack.model.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class StandardDeckTests {

    // No Dependency Injection here
    // Refactoring Required
    DeckOperations deckOperations;
    Deck deck;
    List<Card> cards;

    @BeforeEach
    public void setUp() {
        deckOperations = new DefaultDeckOperations();
        cards = deck.getDeck();
    }

    @Test
    // This Test is flawed as it can pass sometimes and it can't pass sometimes.
    public void verifyShuffleMethodCallInCollections_whenDeckShuffleOperationIsCalled() {
        // Arrange
        Collections mock = Mockito.mock(Collections.class);
        // Act
        deckOperations.shuffle(deck);
        // Assert

    }


    @Test
    // This Test is flawed as it can pass sometimes and it can't pass sometimes.
    public void shouldReturnShuffledCards_whenDeckShuffleOperationIsCalled() {
        // Before Act
        System.out.println(cards);
        Assertions.assertEquals("A", cards.get(0).getCard());
        Assertions.assertEquals("spade", cards.get(0).getSuit());
        // Act
        List<Card> cardsNew = deckOperations.shuffle(deck);
        System.out.println(cardsNew);
        // Assert
        Assertions.assertNotSame("A", cardsNew.get(0).getCard());
        Assertions.assertNotSame("spade", cardsNew.get(0).getSuit());
    }

}
