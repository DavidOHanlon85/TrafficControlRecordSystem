/**
 * 
 */
package p3;

import java.time.Duration;
import java.time.LocalTime;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class Vehicle {

	// Instance Variable

	private static final int ROAD_LENGTH = 20;
	private String licensePlate;
	private LocalTime in;
	private LocalTime out;
	private Type type;
	private String firstName;
	private String lastName;
	private int age;
	private String town;
	private String email;

	// Constructor
	
	
	/**
	 * Constructor with no args
	 */
	public Vehicle() {
		
	}

	/**
	 * @param licensePlate
	 * @param in
	 * @param out
	 * @param type
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param town
	 * @param email
	 */
	public Vehicle(String licensePlate, LocalTime in, LocalTime out, Type type, String firstName, String lastName,
			int age, String town, String email) {
		this.setLicensePlate(licensePlate);
		this.setIn(in);
		this.setOut(out);
		this.setType(type);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAge(age);
		this.setTown(town);
		this.setEmail(email);
	}

	// Getters and Setters

	/**
	 * @return the licensePlate
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 * 
	 * @param licensePlate
	 * @throws IllegalArgumentException
	 */
	public void setLicensePlate(String licensePlate) throws IllegalArgumentException {

		if (licensePlate == null) {
			throw new IllegalArgumentException("LICENSE PLATE IS NULL");
		}
		this.licensePlate = licensePlate;
	}

	/**
	 * @return the in
	 */
	public LocalTime getIn() {
		return in;
	}

	/**
	 * @param in the in to set
	 */
	public void setIn(LocalTime in) {
		this.in = in;
	}

	/**
	 * @return the out
	 */
	public LocalTime getOut() {
		return out;
	}

	/**
	 * @param out the out to set
	 */
	public void setOut(LocalTime out) {
		this.out = out;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) throws IllegalArgumentException {
		if (type == null) {
			throw new IllegalArgumentException("TYPE IS NULL");
		}

		this.type = type;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 * @throws IllegalArgumentException
	 */
	public void setFirstName(String firstName) throws IllegalArgumentException {
		if (firstName == null) {
			throw new IllegalArgumentException("FIRST NAME IS NULL");
		}
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 * @throws IllegalArgumentException
	 */
	public void setLastName(String lastName) throws IllegalArgumentException {
		if (lastName == null) {
			throw new IllegalArgumentException("LAST NAME IS NULL");
		}
		this.lastName = lastName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * 
	 * @param age
	 * @throws IllegalArgumentException
	 */
	public void setAge(int age) throws IllegalArgumentException {
		if (age < 18) {
			throw new IllegalArgumentException("INVALID AGE");
		}
		this.age = age;
	}

	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * 
	 * @param town
	 * @throws IllegalArgumentException
	 */
	public void setTown(String town) throws IllegalArgumentException {
		if (town == null) {
			throw new IllegalArgumentException("TOWN IS NULL");
		}
		this.town = town;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 * @throws IllegalArgumentException
	 */
	public void setEmail(String email) throws IllegalArgumentException {
		if (email == null) {
			throw new IllegalArgumentException("EMAIL IS NULL");
		}
		this.email = email;
	}

	// Methods
	
	public void displayAllDetails() {
		System.out.println("License     :" + getLicensePlate());
		System.out.println("Type        :" + getType());
		System.out.println("Time In     :" + getIn());
		System.out.println("Time Out    :" + getOut());
		System.out.println("First Name  :" + getFirstName());
		System.out.println("Last Name   :"  +getLastName());
		System.out.println("Age         :" + getAge());
		System.out.println("Town        :" + getTown());
		System.out.println("Email       :" + getEmail());
	}
	
	public double calculateAvg() {
		
		Duration duration = Duration.between(in, out);
		
		long diffInMinutes = duration.toMinutes();
		
		double avgSpeed = (double) (ROAD_LENGTH/ ((double) diffInMinutes / 60));
		
		return avgSpeed;
	}
	
	public void displayLicAndSpeed() {
		
		System.out.println("License: " +licensePlate + ". Average Speed: " + calculateAvg());
	}
	
}
