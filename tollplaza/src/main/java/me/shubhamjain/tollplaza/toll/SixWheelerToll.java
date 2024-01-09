/**
 * SixWheelToll class calculates toll when number of wheels in vehicle is 6 and 
 * number of axle is less than 2 
 */
package me.shubhamjain.tollplaza.toll;

import me.shubhamjain.tollplaza.model.TollInvoiceModel;

/**
 * @author SJain
 * @version 1.0
 */
public class SixWheelerToll implements ITollPlaza {

	/** 
	 * Instance of TollInvoiceModel
	 */
	private TollInvoiceModel tollInvoiceModel;
	
	/**
	 * SixWheelsToll constructor for initialization
	 * @param tollInvoiceModel
	 */
	public SixWheelerToll(TollInvoiceModel tollInvoiceModel) {
		this.tollInvoiceModel = tollInvoiceModel;
	}
	
	/**
	 * calculateToll method set the toll fare
	 */
	@Override
	public void calculateToll() {
		this.tollInvoiceModel.setTollFare(500);
	}
	
}
