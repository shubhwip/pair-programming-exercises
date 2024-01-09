/**
 * FourWheelToll class calculates toll when number of wheels in vehicle is 4 
 */
package me.shubhamjain.tollplaza.toll;

import me.shubhamjain.tollplaza.model.TollInvoiceModel;
import me.shubhamjain.tollplaza.util.TollConstants;

/**
 * @author SJain
 * @version 1.0
 */
public class FourWheelerToll implements ITollPlaza {

	/** 
	 * Instance of TollInvoiceModel
	 */
	private TollInvoiceModel tollInvoiceModel;

	/**
	 * FourWheelsToll constructor for initialization
	 * @param tollInvoiceModel
	 */
	public FourWheelerToll(TollInvoiceModel tollInvoiceModel) {
		this.tollInvoiceModel = tollInvoiceModel;
	}

	/**
	 * calculateToll method set the toll fare
	 */
	@Override
	public void calculateToll() {
		if (tollInvoiceModel.getVehicleModel().getVehicleType().toLowerCase().equals(TollConstants.TRANSPORT)) {
			this.tollInvoiceModel.setTollFare(200);
		} else {
			this.tollInvoiceModel.setTollFare(100);
		}
	}
}
