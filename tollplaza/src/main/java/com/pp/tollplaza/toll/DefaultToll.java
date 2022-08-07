/**
 * DefaultToll class calculates toll when vehicle is on Government duty 
 * or in the same state in which it is registered
 */
package com.pp.tollplaza.toll;

import com.pp.tollplaza.model.TollInvoiceModel;

/**
 * @author SJain
 * @version 1.0
 */
public class DefaultToll implements ITollPlaza {

	/** 
	 * Instance of TollInvoiceModel
	 */
	private TollInvoiceModel tollInvoiceModel;

	/**
	 * DefaultToll constructor for initialization
	 * @param tollInvoiceModel
	 */
	public DefaultToll(TollInvoiceModel tollInvoiceModel) {
		this.tollInvoiceModel = tollInvoiceModel;
	}

	/**
	 * calculateToll method set the toll fare
	 */
	@Override
	public void calculateToll() {
		this.tollInvoiceModel.setTollFare(0);
	}

}
