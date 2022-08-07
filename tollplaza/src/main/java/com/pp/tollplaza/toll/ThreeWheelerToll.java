/**
 * ThreeWheelToll class calculates toll when number of wheels in vehicle is 3
 */
package com.pp.tollplaza.toll;

import com.pp.tollplaza.model.TollInvoiceModel;

/**
 * @author SJain
 * @version 1.0
 */
public class ThreeWheelerToll implements ITollPlaza {

	/** 
	 * Instance of TollInvoiceModel
	 */
	private TollInvoiceModel tollInvoiceModel;
	
	/**
	 * ThreeWheelsToll constructor for initialization
	 * @param tollInvoiceModel
	 */
	public ThreeWheelerToll(TollInvoiceModel tollInvoiceModel) {
		this.tollInvoiceModel = tollInvoiceModel;
	}
	
	/**
	 * calculateToll method set the toll fare
	 */
	@Override
	public void calculateToll() {
		this.tollInvoiceModel.setTollFare(50);
	}

}
