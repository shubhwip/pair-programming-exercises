/**
 * FourWheelsTollTest unit test class tests the implementation of FourWheelsToll class
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
public class FourWheelsTollTest {

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
				new VehicleModel("MH14A1234", "T", 4, 1, "N"));
		invoiceDifferentStateNonTransport = new TollInvoiceModel("gujrat",
				new VehicleModel("MH14A1234", "N", 4, 1, "N"));

		tollPlazaDifferentStateTransport = tollPlazaFactory.getTollPlaza(invoiceDifferentStateTransport);
		tollPlazaDifferentStateNonTransport = tollPlazaFactory.getTollPlaza(invoiceDifferentStateNonTransport);
	}

	@Test
	public void testTollPlazaDifferentStateTransport() {
		int actualTollFare = 200;
		tollPlazaDifferentStateTransport.calculateToll();
		int expectedTollFare = invoiceDifferentStateTransport.getTollFare();
		assertEquals(expectedTollFare, actualTollFare);
	}

	@Test
	public void testTollPlazaDifferentStateNonTransport() {
		int actualTollFare = 100;
		tollPlazaDifferentStateNonTransport.calculateToll();
		int expectedTollFare = invoiceDifferentStateNonTransport.getTollFare();
		assertEquals(expectedTollFare, actualTollFare);
	}
}
