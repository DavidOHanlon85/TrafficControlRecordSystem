package p3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 * Start point for the app. Reads in data from csv file and then presents a menu
 * with several functions - searches and a thread based write to file.
 * 
 * @author - Daithi O hAnluain - 15621049
 *
 */
public class StartApp {

	private static final LocalTime TIME_IN_FINISH = LocalTime.of(22, 00);
	private static final LocalTime TIME_IN_START = LocalTime.of(06, 00);
	public static final List<Vehicle> vehicles = new ArrayList<Vehicle>();

	/**
	 * Start point for app
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int input = 0;
		
		readData();
		
		do {
		showMenu();
		Scanner sc = new Scanner(System.in);
		
		input = sc.nextInt();
		
		switch (input) {
		case 1:
			displayAllCarDetailsToScreen();
			break;
		case 2:
			List<Vehicle> HGVs = findAllofType(vehicles, Type.HGV);
			Collections.sort(HGVs, new CompareByLastName());
			displayAllOfListToConsole(HGVs);
			break;
		case 3:
			displayLicenseAndAvgSpeed();
			break;
		case 4:
			int newDriver = 0;
			int olderDriver = 0;
			int Senior = 0;
			
			for (Vehicle v : vehicles) {
				if (v.getAge() >= 18 && v.getAge() <= 29) {
					newDriver++;
				} else if (v.getAge() >= 30 && v.getAge() <= 50) {
					olderDriver++;
				} else {
					Senior++;
				}
			}
			displayAgeCategoryOfDrivers(newDriver, olderDriver, Senior);
			break;
		case 5:
			WriteToFile w = new WriteToFile();
			Thread t1 = new Thread(w);
			
			t1.start();
			break;
		case 6:
			System.out.println("Quitting - Have a great day!");
			
		}
		
		
		} while (input !=6);
		
		
		
	}


	/**
	 * @param newDriver
	 * @param olderDriver
	 * @param Senior
	 */
	public static void displayAgeCategoryOfDrivers(int newDriver, int olderDriver, int Senior) {
		System.out.print("NEW      : ");
		displaystars(newDriver);
		System.out.print("(" + newDriver +")");
		System.out.println();
		System.out.print("OLDER    : ");
		displaystars(olderDriver);
		System.out.print("(" + olderDriver +")");
		System.out.println();
		System.out.print("SENIOR   : ");
		displaystars(Senior);
		System.out.print("(" + Senior +")");
		System.out.println();
	}


	public static void displaystars(int input) {

		for (int i =0; i < input; i++) {
			System.out.print("*");
		}
		
	}


	/**
	 * 
	 */
	public static void displayLicenseAndAvgSpeed() {
		for (Vehicle v : vehicles) {
			v.displayLicAndSpeed();
		}
	}


	/**
	 * This method displays a list of vehicles to console
	 * 
	 * @param HGVs
	 */
	public static void displayAllOfListToConsole(List<Vehicle> HGVs) {
		System.out.println("All HGVs");
		for (Vehicle v : HGVs) {
			v.displayAllDetails();
			System.out.println();
		}
	}

	
	/**
	 * This method finds all vehicles of a type
	 * @param vehicles2
	 * @param hgv
	 */
	public static List<Vehicle> findAllofType(List<Vehicle> vehicles2, Type type) {
		
		List<Vehicle> allOfType = new ArrayList<Vehicle>();
		
		for (Vehicle v : vehicles2) {
			if (v.getType().equals(Type.HGV)) {
				allOfType.add(v);
			}
		}
		return allOfType;
	}

	/**
	 * This method displays all cars to screen
	 */
	public static void displayAllCarDetailsToScreen() {
		System.out.println("All vehicle data");
		System.out.println("All vehicles");
		for (Vehicle v : vehicles) {
			v.displayAllDetails();
			System.out.println();
		}
	}

	/**
	 * Shows the menu and Coordinates the searches and file write
	 * 
	 * @throws Exception
	 */
	public static void showMenu() {
	
		System.out.println("1. Display all vehicle data ");
		System.out.println("2. Display all HGVs (sorted by Surname)");
		System.out.println("3. Display vehicles and average speeds ");
		System.out.println("4. Analyse and display driver age categories ");
		System.out.println("5. Pens - Generate individual penalty notices (new thread needed)  ");
		System.out.println("6. Quit");
		System.out.println("Enter option ...");
		
		
		
	}

	/**
	 * Reads in the data from the csv and maps to the Player class
	 */
	public static void readData() {

		System.out.println("Loading data...");

		int numberOfLinesTotal = 0;
		int numberOfLinesRejected = 0;

		File file = new File("Traffic.csv");

		String line;

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			line = br.readLine(); // remove header

			line = br.readLine(); // read first line of data

			while (line != null) {

				try {
					numberOfLinesTotal++;

					String[] splitDetails = line.split(",");

					Vehicle v = createVehicle(splitDetails);

					vehicles.add(v);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Total lines attempted: " + numberOfLinesTotal);
		System.out.println("Total lines read: " + vehicles.size());

	}

	/**
	 * 
	 * This method creates a vehicle using splitDetails
	 * 
	 * @param splitDetails
	 * @return
	 * @throws Exception
	 */
	public static Vehicle createVehicle(String[] splitDetails) throws Exception {
		Vehicle v = new Vehicle();

		v.setLicensePlate(splitDetails[0].trim());

		String[] localTimeIn = splitDetails[1].split(":");
		String[] localTimeOut = splitDetails[2].split(":");

		LocalTime timeIn = LocalTime.of(Integer.parseInt(localTimeIn[0]), Integer.parseInt(localTimeIn[1]));

		if (timeIn.isBefore(TIME_IN_START) || timeIn.isAfter(TIME_IN_FINISH)) {
			throw new Exception("INVALID ENTRY TIME");
		}

		v.setIn(timeIn);

		LocalTime timeOut = LocalTime.of(Integer.parseInt(localTimeOut[0]),
				Integer.parseInt(localTimeOut[1]));

		v.setOut(timeOut);

		if (splitDetails[3].equalsIgnoreCase("Car")) {
			v.setType(Type.CAR);
		} else if (splitDetails[3].equalsIgnoreCase("HGV")) {
			v.setType(Type.HGV);
		} else {
			throw new Exception("INVALID TYPE");
		}

		String[] splitName = splitDetails[4].split(" ");

		v.setFirstName(splitName[0]);
		v.setLastName(splitName[1]);

		v.setAge(Integer.parseInt(splitDetails[5]));

		v.setTown(splitDetails[6]);

		v.setEmail(splitDetails[7]);
		return v;
		
	}

}