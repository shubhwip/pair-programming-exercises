package me.shubhamjain.cricketsimulation.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.shubhamjain.cricketsimulation.model.Player;
import me.shubhamjain.cricketsimulation.model.Team;
import me.shubhamjain.cricketsimulation.exceptions.InvalidTeamException;
import me.shubhamjain.cricketsimulation.exceptions.NoPlayerOnStrikeException;
import me.shubhamjain.cricketsimulation.gamestrategy.DefaultGameStrategy;
import me.shubhamjain.cricketsimulation.gamestrategy.IGameStrategy;
import me.shubhamjain.cricketsimulation.rules.IRule;
import me.shubhamjain.cricketsimulation.rules.PlayerOutRuleImpl;
import me.shubhamjain.cricketsimulation.rules.StrikeRuleImpl;
import me.shubhamjain.cricketsimulation.runner.DefaultRuleRunner;
import me.shubhamjain.cricketsimulation.runner.IRuleRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>CricketSimulationMain is just runner class to execute APIs of Cricket
 * Simulation Project</h1>
 * 
 * @author Shubham Jain
 * @version 1.0
 */
public class CricketSimulationMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(CricketSimulationMain.class);

	public static void main(String[] args) {
		Team team;
		List<Player> players;
		IGameStrategy gameStrategy;
		IRuleRunner ruleRunner;

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

		IRule[] rules = new IRule[] { new PlayerOutRuleImpl(), new StrikeRuleImpl() };
		gameStrategy.setRules(rules);

		try {
			List<Player> updatedPlayers = ruleRunner.applyRules(players);
			for (Player p : updatedPlayers) {
				LOGGER.info("{}", p);
			}
		} catch (NoPlayerOnStrikeException | InvalidTeamException e) {
			LOGGER.error("Error Occured - {}", e.getMessage());
		}
	}
}
