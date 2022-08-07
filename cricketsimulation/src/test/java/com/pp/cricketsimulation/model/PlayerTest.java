package com.pp.cricketsimulation.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	Player playerOne;
	Player playerTwo;
	Team team;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		team = new Team("Bengaluru", "Chennai", 4, 40, 4);
		playerOne = new Player("Kirat Boli", team, Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), 0, 0, false);
	}

	@Test
	public void testForCreateCopyOfCell() {
		playerTwo = playerOne.createCopy();
		assertEquals("Cell one must be copy to Cell two", playerOne, playerTwo);
	}

	@Test
	public void testForHashCodeAndEqualsMethod() {
		Set<Player> playerSet = new HashSet<>();
		playerTwo = playerOne.createCopy();
		playerSet.add(playerOne);
		assertTrue("Hashcode and equals method must be properly overridden", playerSet.contains(playerTwo));
	}
}
