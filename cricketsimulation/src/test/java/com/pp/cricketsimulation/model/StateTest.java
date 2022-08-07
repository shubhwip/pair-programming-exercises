package com.pp.cricketsimulation.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class StateTest {

	State stateOne;
	State stateTwo;
	Team team;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		team = new Team("Bengaluru", "Chennai", 4, 40, 4);
		stateOne = new State("Kirat Boli", "NS Nodhi", 4, 3, 5, 25, true, 0);
	}

	@Test
	public void testForCreateCopyOfCell() {
		stateTwo = stateOne.createCopy();
		assertEquals("Cell one must be copy to Cell two", stateOne, stateTwo);
	}

	@Test
	public void testForHashCodeAndEqualsMethod() {
		Set<State> stateSet = new HashSet<>();
		stateTwo = stateOne.createCopy();
		stateSet.add(stateOne);
		assertTrue("Hashcode and equals method must be properly overridden", stateSet.contains(stateTwo));
	}
}
