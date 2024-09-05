/**
 * 
 */
package p3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class WriteToFile implements Runnable {
	
	public static final int HGV_SPEED_LIMIT = 50;
	public static final int CAR_SPEED_LIMIT = 60;
	public static List<Vehicle> localCopy = StartApp.vehicles;
	public static List<Vehicle> speeders = new ArrayList<Vehicle>();

	@Override
	public void run() {
		
		findSpeeders();
		
		printSpeedingPenalties();
	}

	/**
	 * This method prints speeding penalties to those speeders
	 */
	public void printSpeedingPenalties() {
		for (Vehicle v : speeders) {
			
			File file = new File(v.getLastName() + "_pen.txt");
			
			try {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				
				bw.write(v.getLicensePlate());
				bw.newLine();
				bw.write(v.getType().toString().toUpperCase());
				bw.newLine();
				bw.newLine();
				bw.write("Dear " + v.getFirstName().toUpperCase() + " " + v.getLastName().toUpperCase());
				bw.newLine();
				String line = String.format("You have been recorded travelling at an average speed of %.0f mph.", v.calculateAvg());
				bw.write(line);
				bw.newLine();
				bw.write("This has resulted in a fixed penalty point notice.");
				bw.newLine();
				bw.newLine();
				bw.write("Depart of Road Traffic Offences");
				
				if (Thread.currentThread().isInterrupted()) {
				bw.close();
				fw.close();
				}
				
				bw.close();
				fw.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

	/**
	 * This method finds speeders in localCopy
	 */
	public void findSpeeders() {
		for (Vehicle v : localCopy) {
			if (v.calculateAvg() > CAR_SPEED_LIMIT && v.getType()==Type.CAR) {
				speeders.add(v);
			} else if (v.calculateAvg() > HGV_SPEED_LIMIT && v.getType()==Type.HGV) {
				speeders.add(v);
			}
		}
	}

}
