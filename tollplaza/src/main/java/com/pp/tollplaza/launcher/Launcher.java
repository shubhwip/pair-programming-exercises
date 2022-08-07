/**
 * Launcher class is used to run the project
 */
package com.pp.tollplaza.launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.pp.tollplaza.exceptions.InvalidDataException;
import com.pp.tollplaza.model.TollInvoiceModel;
import com.pp.tollplaza.toll.ITollPlaza;
import com.pp.tollplaza.factory.TollPlazaFactory;
import com.pp.tollplaza.model.VehicleModel;

/**
 * @author SJain
 *
 */
public class Launcher {

	public static void main(String[] args) {
		String vehicleNumber, vehicleType, govtDuty, state;
		int numberOfWheels, numberOfAxle;
		try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Enter the state in which your toll plaza is located ===> ");
			state = input.readLine();
			while (true) {
				try {
					System.out.println("Enter vehicle number (For e.g. MH14A1234) ===> ");
					vehicleNumber = input.readLine();
					System.out.println("Enter vehicle Type (Options : For Transport : T | For Nontransport : N) ===> ");
					vehicleType = input.readLine();
					System.out.println("Enter number of Wheels (>=2) ===> ");
					numberOfWheels = Integer.parseInt(input.readLine());
					System.out.println("Enter number of axle (for wheels>=4 axle>=2) ===> ");
					numberOfAxle = Integer.parseInt(input.readLine());
					System.out.println("The vehicle is on government duty (Options : For Yes : Y | For No : N) ===> ");
					govtDuty = input.readLine();
					TollInvoiceModel tollInvoiceModel = new TollInvoiceModel(state,
							new VehicleModel(vehicleNumber, vehicleType, numberOfWheels, numberOfAxle, govtDuty));
					TollPlazaFactory tollFactory = new TollPlazaFactory();
					ITollPlaza tollPlaza = tollFactory.getTollPlaza(tollInvoiceModel);
					tollPlaza.calculateToll();
					System.out.println("Toll fare for the vehicle is ===> " + tollInvoiceModel.getTollFare() + "INR");
				} catch (InvalidDataException e) {
					System.out.println("Error: " + e.getMessage());
				} catch (NumberFormatException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
