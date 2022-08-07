/**
 * DefaultTollTest unit test class tests the implementation of DefaultToll class
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
public class DefaultTollTest {

	TollPlazaFactory tollPlazaFactory;

	TollInvoiceModel invoiceOnGovtDuty;
	TollInvoiceModel invoiceSameState;

	ITollPlaza tollPlazaOnGovtDuty;
	ITollPlaza tollPlazaSameState;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tollPlazaFactory = new TollPlazaFactory();

		invoiceOnGovtDuty = new TollInvoiceModel("maharashtra", new VehicleModel("MH14A1234", "T", 3, 1, "Y"));
		invoiceSameState = new TollInvoiceModel("maharashtra", new VehicleModel("MH14A1234", "T", 3, 1, "N"));

		tollPlazaOnGovtDuty = tollPlazaFactory.getTollPlaza(invoiceOnGovtDuty);
		tollPlazaSameState = tollPlazaFactory.getTollPlaza(invoiceSameState);
	}

	@Test
	public void testTollPlazaOnGovtDuty() {
		int actualTollFare = 0;
		tollPlazaOnGovtDuty.calculateToll();
		int expectedTollFare = invoiceOnGovtDuty.getTollFare();
		assertEquals(expectedTollFare, actualTollFare);
	}

	@Test
	public void tollPlazaSameState() {
		int actualTollFare = 0;
		tollPlazaSameState.calculateToll();
		int expectedTollFare = invoiceSameState.getTollFare();
		assertEquals(expectedTollFare, actualTollFare);
	}
}
