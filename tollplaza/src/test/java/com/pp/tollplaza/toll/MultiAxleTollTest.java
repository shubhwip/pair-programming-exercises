/**
 * MultiAxleTollTest unit test class tests the implementation of MultiAxleToll class
 */
package com.pp.tollplaza.toll;

import static org.junit.Assert.assertEquals;

import com.pp.tollplaza.model.TollInvoiceModel;
import org.junit.Before;
import org.junit.Test;

import com.pp.tollplaza.factory.TollPlazaFactory;
import com.pp.tollplaza.model.VehicleModel;

/**
 * @author SJain
 * @version 1.0
 */
public class MultiAxleTollTest {

	TollPlazaFactory tollPlazaFactory;

	TollInvoiceModel invoiceDifferentStateTransport;
	TollInvoiceModel invoiceDifferentStateNonTransport;

	ITollPlaza tollPlazaDifferentStateTransport;
	ITollPlaza tollPlazaDifferentStateNonTransport;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tollPlazaFactory = new TollPlazaFactory();

		invoiceDifferentStateTransport = new TollInvoiceModel("gujrat",
				new VehicleModel("MH14A1234", "T", 6, 3, "N"));

		tollPlazaDifferentStateTransport = tollPlazaFactory.getTollPlaza(invoiceDifferentStateTransport);
	}

	@Test
	public void testTollPlazaDifferentStateTransport() {
		int actualTollFare = 800;
		tollPlazaDifferentStateTransport.calculateToll();
		int expectedTollFare = invoiceDifferentStateTransport.getTollFare();
		assertEquals(expectedTollFare, actualTollFare);
	}
}
