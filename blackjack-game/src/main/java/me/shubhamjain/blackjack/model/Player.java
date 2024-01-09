package me.shubhamjain.blackjack.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Player {
    private int id;
    private int bet;
    private int wallet;
    private boolean active;
    private boolean bust;
    private int score;
    private boolean gotSpecialCard;
    private List<Card> hand;
    private List<Card> holeCards;
}
