package me.shubhamjain.blackjack.manager;

import me.shubhamjain.blackjack.deck.DeckConstants;
import me.shubhamjain.blackjack.model.Card;
import me.shubhamjain.blackjack.model.FaceValues;
import me.shubhamjain.blackjack.model.Player;

import java.util.ArrayList;
import java.util.List;

public class DefaultBlackJackHelper implements BlackJackHelper {
    @Override
    public int calculateScores(List<Card> cards) {
        int score = 0;
        boolean foundSpecialCard = false;
        for (Card card : cards) {
            if (card.getRank().equals(DeckConstants.SPECIAL_CARD)) {
                foundSpecialCard = true;
                continue;
            } else
                // Optimize FaceCards Class
                score += FaceValues.valueOf(card.getRank()).getValue(card.getRank());
        }
        if (foundSpecialCard) {
            List<Integer> possibleScores = new ArrayList<>();
            for (Integer value : DeckConstants.SPECIAL_CARD_VALUES) {
                int s = value + score;
                if (s == BlackJackConstants.WINNING_SCORE)
                    return s;
                possibleScores.add(s);
            }
            for (int i = possibleScores.size() - 1; i >= 0; i--) {
                if (possibleScores.get(i) < BlackJackConstants.WINNING_SCORE)
                    return possibleScores.get(i);
            }
        }
        return score;
    }

    public boolean busted(Player p) {
        return calculateScores(p.getHand()) > BlackJackConstants.WINNING_SCORE ? true : false;
    }

}
