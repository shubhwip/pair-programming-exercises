package me.shubhamjain.blackjack.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Card {
    String rank;
    String suit;
}
