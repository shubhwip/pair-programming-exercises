package com.pp.blackjack;

import com.pp.blackjack.manager.*;
import com.pp.blackjack.model.Dealer;
import com.pp.blackjack.operation.OperationFactory;
import com.pp.blackjack.runner.DefaultGameRunner;
import com.pp.blackjack.runner.GameRunner;
import com.pp.blackjack.deck.Deck;
import com.pp.blackjack.deck.StandardDeck;
import com.systemdesign.blackjack.manager.*;
import com.pp.blackjack.model.Card;
import com.pp.blackjack.model.Player;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        Deck deck = new StandardDeck();
        List<Player> players = new ArrayList<>();
        players.add(new Player(0, 10, 100, true, false, 0, new ArrayList<Card>(), new ArrayList<Card>()));
        players.add(new Player(1, 10, 100, true, false, 0, new ArrayList<Card>(), new ArrayList<Card>()));
        players.add(new Player(2, 10, 100, true, false, 0, new ArrayList<Card>(), new ArrayList<Card>()));
        Dealer dealer = new Dealer(0, 10, true, false, 0, new ArrayList<Card>(), new ArrayList<Card>());
        BlackJackStrategy blackJackStrategy = new DefaultBlackJackStrategy();
        OperationFactory operationFactory = new OperationFactory();
        BlackJackHelper blackJackHelper = new DefaultBlackJackHelper();
        BlackJackManager blackJackManager = new DefaultBlackJackManager(deck, players, dealer, blackJackStrategy, operationFactory, blackJackHelper);
        GameRunner gameRunner = new DefaultGameRunner(blackJackManager);
        gameRunner.start();
    }
}
