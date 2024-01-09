package me.shubhamjain.blackjack.manager;

import me.shubhamjain.blackjack.deck.Deck;
import me.shubhamjain.blackjack.deck.EmptyDeckException;
import me.shubhamjain.blackjack.model.Dealer;
import me.shubhamjain.blackjack.model.Player;
import me.shubhamjain.blackjack.operation.Operation;
import me.shubhamjain.blackjack.operation.OperationFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@Slf4j
public class DefaultBlackJackManager implements BlackJackManager {

    Deck deck;
    List<Player> players;
    Dealer dealer;
    BlackJackStrategy blackJackStrategy;
    OperationFactory operationFactory;
    BlackJackHelper blackJackHelper;

    public void begin() {
        // Shuffle the Deck
        deck.shuffleDeck();
        // Distribute Two Cards to each player including dealer
        try {
            for (Player p : players) {
                p.getHand().add(deck.take());
                p.getHand().add(deck.take());
            }
            dealer.getHand().add(deck.take());
            dealer.getHoleCards().add(deck.take());
        } catch (EmptyDeckException e) {
            log.error("Deck is Empty, Exiting");
        }
        // Reveal Cards Here
        for (Player p : players) {
            log.info("Player ID and Cards {}", p.getId(), p.getHand());
        }
        log.info("Dealer Data {}", dealer.getHand());
    }

    @Override
    public void calculateScore() {
        // One pass to check if any player has got 21 already.
        // Later you need to check if dealer also got 21 then you will get your own money back only and return the remaining.
        for (Player p : players) {
            int score = blackJackHelper.calculateScores(p.getHand());
            p.setScore(score);
            if (score == BlackJackConstants.WINNING_SCORE) {
                // Change it to BigDecimal
                p.setWallet((int) (p.getWallet() + p.getBet() * 1.5));
                p.setActive(false);
            }
        }
        for (Player p : players) {
            log.info("Player ID {} and Score {}", p.getId(), p.getScore());
        }
    }

    public void startRound() {
        // Beat the dealer
        // Final pass for all players and dealer
        // After a round dealer opens downcards and adds to faceup cards and if it is < 16, he takes a new card, if it is 17 or higher
        // then no card
        // If the dealer busts then every player wins twice their bet.
        // Higher than dealer wins twice their bet and every one else loses.
        // Once player's hand get higher than 21, player is bust and bet money goes to dealer
        // stay for no more cards
        log.info("Select one of the operations");
        log.info("Type 0 for HIT");
        log.info("Type 1 for STAND");
        log.info("Type 2 for DOUBLE DOWN");
        log.info("Type 3 for SURRENDER");
        Scanner scanner = new Scanner(System.in);
        Operation operation;
        try {
            for (Player p : players) {
                if (p.isActive() && !p.isBust()) {
                    int move = scanner.nextInt();
                    operation = operationFactory.getInstance(move);
                    // In startRoundMethod
                    // We will take input from each player one by one and execute their input.
                    while (move == 2) {
                        operation.apply(p, deck);
                        p.setScore(blackJackHelper.calculateScores(p.getHand()));
                        move = scanner.nextInt();
                    }
                    operation.apply(p, deck);
                }
            }
            if (!dealer.isBust() && dealer.isActive()) {
                int move = scanner.nextInt();
                operation = operationFactory.getInstance(move);
                // In startRoundMethod
                // We will take input from each player one by one and execute their input.
                while (move == 2) {
                    operation.apply(dealer, deck);
                    move = scanner.nextInt();
                }
                operation.apply(dealer, deck);
            }
        } finally {
            scanner.close();
        }
    }

    @Override
    public void settle() {
        if (dealer.isBust()) {
            for (Player p : players) {
                if (!p.isBust() && p.isActive()) {
                    p.setWallet(p.getWallet() * 2);
                }
            }
        } else {
            for (Player p : players) {
                if (p.isActive() && !p.isBust()) {
                    if (p.getScore() > dealer.getScore()) {
                        p.setWallet(p.getWallet() + p.getBet() * 2);
                    } else if (p.getScore() == dealer.getScore()) {
                        p.setWallet(p.getWallet() + p.getBet());
                    }
                }
            }
        }
    }
}
