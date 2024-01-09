/**
 * VehicleModel class defines the details of vehicle card 
 * present on registration card of vehicle
 */
package me.shubhamjain.tollplaza.model;

import java.util.Objects;

/**
 * @author SJain
 * @version 1.0
 */
public class VehicleModel {

	/** Vehicle Number on the registration card of vehicle */
	private String vehicleNumber;

	/** Vehicle Type on the registration card of vehicle */
	private String vehicleType;

	/** Number of wheels mentioned in the registration card of vehicle */
	private int numberOfWheels;

	/** Number of Axle mentioned in the registration card of vehicle */
	private int numberOfAxle;

	/** boolean to check if vehicle is on Government. duty or not */
	private String isOnGovtDuty;

	/**
	 * Vehicle Model Constructor
	 * 
	 * @param vehicleNumber
	 *            vehicle number on the registration card : MH14A1234
	 * @param vehicleType
	 *            vehicle type : (Transport || Non Transport)
	 * @param numberOfWheels
	 *            wheels in the vehicle (>=2)
	 * @param numberOfAxle
	 *            axles (>=2) for wheels >=4
	 * @param isOnGovetDuty
	 *            true if vehicle is on Government duty
	 */
	public VehicleModel(String vehicleNumber, String vehicleType, int numberOfWheels, int numberOfAxle,
			String isOnGovetDuty) {
		this.setVehicleNumber(vehicleNumber);
		this.setVehicleType(vehicleType);
		this.setNumberOfWheels(numberOfWheels);
		this.setNumberOfAxle(numberOfAxle);
		this.setOnGovtDuty(isOnGovetDuty);
	}

	/**
	 * @return the vehicleNumber
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * @param vehicleNumber
	 *            the vehicleNumber to set
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * @return the vehicleType
	 */
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * @param vehicleType
	 *            the vehicleType to set
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * @return the numberOfWheels
	 */
	public int getNumberOfWheels() {
		return numberOfWheels;
	}

	/**
	 * @param numberOfWheels
	 *            the numberOfWheels to set
	 */
	public void setNumberOfWheels(int numberOfWheels) {
		this.numberOfWheels = numberOfWheels;
	}

	/**
	 * @return the numberOfAxle
	 */
	public int getNumberOfAxle() {
		return numberOfAxle;
	}

	/**
	 * @param numberOfAxle
	 *            the numberOfAxle to set
	 */
	public void setNumberOfAxle(int numberOfAxle) {
		this.numberOfAxle = numberOfAxle;
	}

	/**
	 * @return the isOnGovtDuty
	 */
	public String isOnGovtDuty() {
		return isOnGovtDuty;
	}

	/**
	 * @param isOnGovtDuty
	 *            the isOnGovtDuty to set
	 */
	private void setOnGovtDuty(String isOnGovtDuty) {
		this.isOnGovtDuty = isOnGovtDuty;
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
		return Objects.hash(vehicleNumber, vehicleType, numberOfWheels, numberOfAxle, isOnGovtDuty);
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
		VehicleModel vehicleModel = (VehicleModel) obj;
		return vehicleNumber == vehicleModel.vehicleNumber && Objects.equals(vehicleType, vehicleModel.vehicleType)
				&& Objects.equals(numberOfWheels, vehicleModel.numberOfWheels)
				&& Objects.equals(numberOfAxle, vehicleModel.numberOfAxle)
				&& Objects.equals(isOnGovtDuty, vehicleModel.isOnGovtDuty);
	}

	/**
	 * This method is used to create copy of VehicleModel
	 * 
	 * @return VehicleModel
	 */
	public VehicleModel createCopy() {
		return new VehicleModel(vehicleNumber, vehicleType, numberOfWheels, numberOfAxle, isOnGovtDuty);
	}

	/**
	 * toString method returns string representation of VehicleModel object.
	 */
	@Override
	public String toString() {
		return "VehicleNumber: '" + this.vehicleNumber + "', VehicleType: '" + this.vehicleType
				+ "', Number of Wheels: '" + this.numberOfWheels + "', Number of Axle: '" + this.numberOfAxle + "'";
	}
}
