package me.shubhamjain.blackjack.deck;

import java.util.Arrays;
import java.util.List;

// Review By Shubham Jain
// All fields seems to be mutable, we should make them immutable constants
// Define private constructor in class to avoid initialisation in class
public class DeckConstants {

    // Todo : Mutable
    public static String[] SUITS = {"spade", "club", "heart", "diamond"};
    // Todo : Mutable
    public static String[] RANKS = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    // Todo : Mutable
    public static String SPECIAL_CARD = "A";
    // Make sure to put Special card values in increasing order
    // Todo : Mutable
    // Todo : Use List.of()
    public static List<Integer> SPECIAL_CARD_VALUES = Arrays.asList(1, 11);
}
