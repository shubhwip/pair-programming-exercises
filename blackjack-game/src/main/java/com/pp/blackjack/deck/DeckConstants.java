package com.pp.blackjack.deck;

import java.util.Arrays;
import java.util.List;

public class DeckConstants {

    public static String[] SUITS = {"spade", "club", "heart", "diamond"};
    public static String[] RANKS = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public static String SPECIAL_CARD = "A";
    // Make sure to put Special card values in increasing order
    public static List<Integer> SPECIAL_CARD_VALUES = Arrays.asList(1, 11);
}
