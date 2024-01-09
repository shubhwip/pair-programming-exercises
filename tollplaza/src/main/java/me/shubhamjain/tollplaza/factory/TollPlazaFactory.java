/**
 * TollPlazaFactory class is used to get instance of toll plaza on the basis of vehicle specifications
 */
package me.shubhamjain.tollplaza.factory;

import me.shubhamjain.tollplaza.exceptions.InvalidDataException;
import me.shubhamjain.tollplaza.model.TollInvoiceModel;
import me.shubhamjain.tollplaza.util.TollConstants;
import me.shubhamjain.tollplaza.util.TollPlazaUtils;
import me.shubhamjain.tollplaza.model.VehicleModel;
import me.shubhamjain.tollplaza.toll.DefaultToll;
import me.shubhamjain.tollplaza.toll.FourWheelerToll;
import me.shubhamjain.tollplaza.toll.ITollPlaza;
import me.shubhamjain.tollplaza.toll.MultiAxleToll;
import me.shubhamjain.tollplaza.toll.SixWheelerToll;
import me.shubhamjain.tollplaza.toll.ThreeWheelerToll;
import me.shubhamjain.tollplaza.toll.TwoWheelerToll;

/**
 * @author SJain
 * @version 1.0
 */
public class TollPlazaFactory {
	public ITollPlaza getTollPlaza(TollInvoiceModel tollInvoiceModel) throws InvalidDataException {
		int numberOfWheels = tollInvoiceModel.getVehicleModel().getNumberOfWheels();
		int numberOfAxle = tollInvoiceModel.getVehicleModel().getNumberOfAxle();
		String state = tollInvoiceModel.getState();
		VehicleModel vehicleModel = tollInvoiceModel.getVehicleModel();
		String isOnGovtDuty = vehicleModel.isOnGovtDuty().toLowerCase();

		if (!TollPlazaUtils.isValidData(tollInvoiceModel))
			throw new InvalidDataException(TollConstants.INVALID_DATA);

		if (TollPlazaUtils.isStateSame(vehicleModel, state) || isOnGovtDuty.equals(TollConstants.YES))
			return new DefaultToll(tollInvoiceModel);
		else if (numberOfWheels == 2)
			return new TwoWheelerToll(tollInvoiceModel);
		else if (numberOfWheels == 3)
			return new ThreeWheelerToll(tollInvoiceModel);
		else if (numberOfWheels == 4)
			return new FourWheelerToll(tollInvoiceModel);
		else if (numberOfWheels == 6 && numberOfAxle < 2)
			return new SixWheelerToll(tollInvoiceModel);
		else if (numberOfWheels >= 4 && numberOfAxle >= 2)
			return new MultiAxleToll(tollInvoiceModel);
		else
			throw new InvalidDataException(TollConstants.UNDEFINED_TOLL);
	}
}
