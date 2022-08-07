/**
 * TollInvoiceModel class defines the model of invoice of toll booth 
 * where the state is current state in which toll is located and 
 * fare is amount which is calculated for a vehicle
 */
package com.pp.tollplaza.model;

import java.util.Objects;

/**
 * @author SJain
 * @version 1.0
 */
public class TollInvoiceModel {

	/** State of tollPlaza */
	private String state;

	/** toll fare */
	private int tollFare;

	/** Vehicle Model containing vehicle registration card details */
	private VehicleModel vehicleModel;

	/**
	 * TollInvoiceModel constructor to initialize state and vehicle model
	 * 
	 * @param state
	 *            current state of tollPlaza
	 * @param vehicleModel
	 *            vehicle model
	 */
	public TollInvoiceModel(String state, VehicleModel vehicleModel) {
		this.setState(state);
		this.setVehicleModel(vehicleModel);
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	private void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the tollFare
	 */
	public int getTollFare() {
		return tollFare;
	}

	/**
	 * @param tollFare
	 *            the tollFare to set
	 */
	public void setTollFare(int tollFare) {
		this.tollFare = tollFare;
	}

	/**
	 * @return the vehicleModel
	 */
	public VehicleModel getVehicleModel() {
		return vehicleModel;
	}

	/**
	 * @param vehicleModel
	 *            the vehicleModel to set
	 */
	private void setVehicleModel(VehicleModel vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 * 
	 * @return result
	 */
	@Override
	public int hashCode() {
		return Objects.hash(state, tollFare, vehicleModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * @param obj
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VehicleModel)) {
			return false;
		}
		TollInvoiceModel tollInvoiceModel = (TollInvoiceModel) obj;
		return state == tollInvoiceModel.state && Objects.equals(tollFare, tollInvoiceModel.tollFare)
				&& Objects.equals(vehicleModel, tollInvoiceModel.vehicleModel);
	}

	/**
	 * This method is used to create copy of TollInvoiceModel
	 * 
	 * @return TollInvoiceModel
	 */
	public TollInvoiceModel createCopy() {
		return new TollInvoiceModel(state, vehicleModel);
	}

	/**
	 * toString method returns string representation of TollInvoiceModel object.
	 */
	@Override
	public String toString() {
		return "State: '" + this.state + "', VehicleModel: '" + this.vehicleModel + "', TollFare: '" + this.tollFare
				+ "'";
	}
}
