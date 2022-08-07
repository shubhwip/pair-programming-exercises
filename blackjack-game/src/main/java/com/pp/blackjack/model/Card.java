package com.pp.blackjack.model;

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
