package com.pp.blackjack.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Dealer {
    private int id;
    private int wallet;
    private boolean active;
    private boolean bust;
    private int score;
    private List<Card> hand;
    private boolean gotSpecialCard;
    private List<Card> holeCards;
}
