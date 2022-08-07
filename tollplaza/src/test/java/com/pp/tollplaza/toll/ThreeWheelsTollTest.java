/**
 * ThreeWheelsTollTest unit test class tests the implementation of ThreeWheelsToll class
 */
package com.pp.tollplaza.toll;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.pp.tollplaza.factory.TollPlazaFactory;
import com.pp.tollplaza.model.TollInvoiceModel;
import com.pp.tollplaza.model.VehicleModel;

/**
 * @author SJain
 * @version 1.0
 */
public class ThreeWheelsTollTest {

	TollPlazaFactory tollPlazaFactory;

	TollInvoiceModel invoiceDifferentState;

	ITollPlaza tollPlazaDifferentState;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tollPlazaFactory = new TollPlazaFactory();

		invoiceDifferentState = new TollInvoiceModel("gujrat", new VehicleModel("MH14A1234", "T", 3, 1, "N"));

		tollPlazaDifferentState = tollPlazaFactory.getTollPlaza(invoiceDifferentState);
	}

	@Test
	public void tollPlazaDifferentState() {
		int actualTollFare = 50;
		tollPlazaDifferentState.calculateToll();
		int expectedTollFare = invoiceDifferentState.getTollFare();
		assertEquals(expectedTollFare, actualTollFare);
	}
}
