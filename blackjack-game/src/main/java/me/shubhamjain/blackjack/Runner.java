package me.shubhamjain.blackjack;

import me.shubhamjain.blackjack.deck.Deck;
import me.shubhamjain.blackjack.deck.StandardDeck;
import com.pp.blackjack.manager.*;
import me.shubhamjain.blackjack.manager.*;
import me.shubhamjain.blackjack.model.Card;
import me.shubhamjain.blackjack.model.Dealer;
import me.shubhamjain.blackjack.model.Player;
import me.shubhamjain.blackjack.operation.OperationFactory;
import me.shubhamjain.blackjack.runner.DefaultGameRunner;
import me.shubhamjain.blackjack.runner.GameRunner;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        Deck deck = new StandardDeck();
        List<Player> players = new ArrayList<>();
        players.add(new Player(0, 10, 100, true, false, 0, false, new ArrayList<Card>(), new ArrayList<Card>()));
        players.add(new Player(1, 10, 100, true, false, 0, false, new ArrayList<Card>(), new ArrayList<Card>()));
        players.add(new Player(2, 10, 100, true, false, 0, false, new ArrayList<Card>(), new ArrayList<Card>()));
        Dealer dealer = new Dealer(0, 10, true, false, 0, new ArrayList<Card>(), false, new ArrayList<Card>());
        BlackJackStrategy blackJackStrategy = new DefaultBlackJackStrategy();
        OperationFactory operationFactory = new OperationFactory();
        BlackJackHelper blackJackHelper = new DefaultBlackJackHelper();
        BlackJackManager blackJackManager = new DefaultBlackJackManager(deck, players, dealer, blackJackStrategy, operationFactory, blackJackHelper);
        GameRunner gameRunner = new DefaultGameRunner(blackJackManager);
        gameRunner.start();
    }
}
