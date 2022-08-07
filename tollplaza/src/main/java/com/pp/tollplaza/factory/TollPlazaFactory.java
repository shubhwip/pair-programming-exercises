/**
 * TollPlazaFactory class is used to get instance of toll plaza on the basis of vehicle specifications
 */
package com.pp.tollplaza.factory;

import com.pp.tollplaza.exceptions.InvalidDataException;
import com.pp.tollplaza.model.TollInvoiceModel;
import com.pp.tollplaza.util.TollConstants;
import com.pp.tollplaza.util.TollPlazaUtils;
import com.pp.tollplaza.model.VehicleModel;
import com.pp.tollplaza.toll.DefaultToll;
import com.pp.tollplaza.toll.FourWheelerToll;
import com.pp.tollplaza.toll.ITollPlaza;
import com.pp.tollplaza.toll.MultiAxleToll;
import com.pp.tollplaza.toll.SixWheelerToll;
import com.pp.tollplaza.toll.ThreeWheelerToll;
import com.pp.tollplaza.toll.TwoWheelerToll;

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
