package com.pp.cricketsimulation.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.pp.cricketsimulation.gamestrategy.DefaultGameStrategy;
import com.pp.cricketsimulation.gamestrategy.IGameStrategy;
import com.pp.cricketsimulation.model.Player;
import com.pp.cricketsimulation.model.State;
import com.pp.cricketsimulation.model.Team;
import com.pp.cricketsimulation.runner.DefaultRuleRunner;
import com.pp.cricketsimulation.runner.IRuleRunner;

public class StrikeRuleOutImplTest {

	Team team;
	List<Player> players;
	IGameStrategy gameStrategy;
	IRuleRunner ruleRunner;

	@Before
	public void setup() {
		team = new Team("Bengaluru", "Chennai", 4, 40, 4);
		gameStrategy = new DefaultGameStrategy();
		ruleRunner = new DefaultRuleRunner(gameStrategy);
		players = new ArrayList<>();
		players.add(
				new Player("Kirat Boli", team, Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
		players.add(
				new Player("NS Nodhi", team, Arrays.asList(10.0, 40.0, 20.0, 5.0, 10.0, 1.0, 4.0, 10.0), 0, 0, false));
		players.add(
				new Player("R Rumrah", team, Arrays.asList(20.0, 30.0, 15.0, 5.0, 5.0, 1.0, 4.0, 20.0), 0, 0, false));
		players.add(new Player("Shashi Henra", team, Arrays.asList(30.0, 25.0, 5.0, 0.0, 5.0, 1.0, 4.0, 30.0), 0, 0,
				false));
	}

	@Test
	public void testStrikeRuleWithOverComplete() {
		State currentState = new State("Kirat Boli", "NS Nodhi", 2, 4, 6, 25, false, 0);
		IRule rule = new StrikeRuleImpl();
		State nextState = rule.nextState(currentState, players);
		assertEquals("NS Nodhi", nextState.getCurrentStriker());
	}

	@Test
	public void testStrikeRuleWithOddRuns() {
		State currentState = new State("Kirat Boli", "NS Nodhi", 1, 4, 5, 25, false, 0);
		IRule rule = new StrikeRuleImpl();
		State nextState = rule.nextState(currentState, players);
		assertEquals("NS Nodhi", nextState.getCurrentStriker());
	}

	@Test
	public void testStrikeRuleWithOddRunsAndOversComplete() {
		State currentState = new State("NS Nodhi", "Kirat Boli", 1, 4, 6, 25, false, 1);
		IRule rule = new StrikeRuleImpl();
		State nextState = rule.nextState(currentState, players);
		assertEquals("NS Nodhi", nextState.getCurrentStriker());
	}

}
