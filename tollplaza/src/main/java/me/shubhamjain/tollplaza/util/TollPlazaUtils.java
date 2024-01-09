/**
 * TollPlazaUtils class contains utility methods related to tollPlaza
 */
package me.shubhamjain.tollplaza.util;

import me.shubhamjain.tollplaza.enums.StateCodeMapping;
import me.shubhamjain.tollplaza.model.TollInvoiceModel;
import me.shubhamjain.tollplaza.exceptions.InvalidDataException;
import me.shubhamjain.tollplaza.model.VehicleModel;

/**
 * @author SJain
 * @version 1.0
 */
public class TollPlazaUtils {

	/**
	 * Private constructor for TollPlazaUtils to limit initialization of this
	 * class as it is utility class
	 */
	private TollPlazaUtils() {
	}

	/**
	 * isStateSame method parses the vehicle number and get state code and check
	 * if states are same
	 * 
	 * @param state
	 * @return true if the state is same as mentioned in vehicle number
	 * @throws InvalidDataException
	 */
	public static boolean isStateSame(VehicleModel vehicleModel, String state) throws InvalidDataException {
		String code = vehicleModel.getVehicleNumber().substring(0, 2);
		String stateName = StateCodeMapping.getStateName(code);
		if (stateName == null)
			throw new InvalidDataException(TollConstants.INVALID_VEHICLE_NUMBER);
		if (stateName.toLowerCase().equals(state)) {
			return true;
		}
		return false;
	}

	/**
	 * isValidData checks for validity of data
	 * 
	 * @param tollInvoiceModel
	 * @return It returns true if data is valid
	 */
	public static boolean isValidData(TollInvoiceModel tollInvoiceModel) {
		boolean isValid = true;
		boolean isWheelsLessThanTwo = tollInvoiceModel.getVehicleModel().getNumberOfWheels() < 2;
		boolean isAxleValuesWrong = tollInvoiceModel.getVehicleModel().getNumberOfWheels() < 2
				&& tollInvoiceModel.getVehicleModel().getNumberOfAxle() < 4;
		boolean isGovtDutyWrong = !tollInvoiceModel.getVehicleModel().isOnGovtDuty().toLowerCase()
				.equals(TollConstants.YES)
				&& !tollInvoiceModel.getVehicleModel().isOnGovtDuty().toLowerCase().equals(TollConstants.NO);

		if (isWheelsLessThanTwo || isAxleValuesWrong || isGovtDutyWrong)
			isValid = false;
		return isValid;
	}
}
