/**
 * TwoWheelsToll class calculates toll when number of wheels in vehicle is 2
 */
package me.shubhamjain.tollplaza.toll;

import me.shubhamjain.tollplaza.model.TollInvoiceModel;

/**
 * @author SJain
 * @version 1.0
 */
public class TwoWheelerToll implements ITollPlaza {

	/** 
	 * Instance of TollInvoiceModel
	 */
	private TollInvoiceModel tollInvoiceModel;
	
	/**
	 * TwoWheelsToll constructor for initialization
	 * @param tollInvoiceModel
	 */
	public TwoWheelerToll(TollInvoiceModel tollInvoiceModel) {
		this.tollInvoiceModel = tollInvoiceModel;
	}
	
	/**
	 * calculateToll method set the toll fare
	 */
	@Override
	public void calculateToll() {
		this.tollInvoiceModel.setTollFare(20);
	}

}