/**
 * MultiAxleToll class calculates toll when number of wheels in vehicle is greater than and equal to 4 and 
 * number of axle is greater than and equal to 2 
 */
package me.shubhamjain.tollplaza.toll;

import me.shubhamjain.tollplaza.model.TollInvoiceModel;

/**
 * @author SJain
 * @version 1.0
 */
public class MultiAxleToll implements ITollPlaza {

	/** 
	 * Instance of TollInvoiceModel
	 */
	private TollInvoiceModel tollInvoiceModel;

	/**
	 * MultiAxleToll constructor for initialization
	 * @param tollInvoiceModel
	 */
	public MultiAxleToll(TollInvoiceModel tollInvoiceModel) {
		this.tollInvoiceModel = tollInvoiceModel;
	}

	/**
	 * calculateToll method set the toll fare
	 */
	@Override
	public void calculateToll() {
		this.tollInvoiceModel.setTollFare(500 + tollInvoiceModel.getVehicleModel().getNumberOfAxle() * 100);
	}
}
