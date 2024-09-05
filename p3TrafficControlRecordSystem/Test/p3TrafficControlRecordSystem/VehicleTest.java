package p3TrafficControlRecordSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import p3.Type;
import p3.Vehicle;

class VehicleTest {

	// input data

	Vehicle v;

	String validLicensePlate, validFirstName, validLastName, validTown, validEmail;
	LocalTime in, out;
	Type car;
	Type HGV;
	int validage, invalidage, invalidagenegative;

	@BeforeEach
	void setUp() throws Exception {

		validLicensePlate = "WPM 4567";
		validFirstName = "David";
		validLastName = "O'Hanlon";
		validTown = "Belfast";
		validEmail = "david@david.com";

		in = LocalTime.of(8, 22);
		out = LocalTime.of(3, 44);

		car = Type.CAR;
		HGV = Type.HGV;

		validage = 50;
		invalidage = 14;
		invalidagenegative = -6;

		v = new Vehicle(validLicensePlate, in, out, HGV, validFirstName, validLastName, validage, validTown,
				validEmail);

	}

	@Test
	void testVehicleConstructorValid() {
		assertEquals(validLicensePlate, v.getLicensePlate());
		assertEquals(in, v.getIn());
		assertEquals(out, v.getOut());
		assertEquals(HGV, v.getType());
		assertEquals(validFirstName, v.getFirstName());
		assertEquals(validLastName, v.getLastName());
		assertEquals(validage, v.getAge());
		assertEquals(validTown, v.getTown());
		assertEquals(validEmail, v.getEmail());
	}

	@Test
	void testVehicleConstructorInvalid() {

		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setLicensePlate(null);
		});

		assertEquals("LICENSE PLATE IS NULL", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setType(null);
		});

		assertEquals("TYPE IS NULL", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setFirstName(null);
		});

		assertEquals("FIRST NAME IS NULL", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setLastName(null);
		});

		assertEquals("LAST NAME IS NULL", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setAge(invalidage);
		});

		assertEquals("INVALID AGE", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setAge(invalidagenegative);
		});

		assertEquals("INVALID AGE", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setTown(null);
		});

		assertEquals("TOWN IS NULL", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setEmail(null);
		});

		assertEquals("EMAIL IS NULL", exp.getMessage());

	}

	@Test
	void testSetGetLicensePlate() {
		v.setLicensePlate(validLicensePlate);
		assertEquals(validLicensePlate, v.getLicensePlate());
	}

	@Test
	void testSetLicensePlateInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setLicensePlate(null);
		});

		assertEquals("LICENSE PLATE IS NULL", exp.getMessage());
	}

	@Test
	void testSetGetIn() {
		v.setIn(in);
		assertEquals(in, v.getIn());
	}

	@Test
	void testSetGetOut() {
		v.setOut(out);
		assertEquals(out, v.getOut());
	}

	@Test
	void testSetGetType() {
		v.setType(HGV);
		assertEquals(HGV, v.getType());
	}

	@Test
	void testSetTypeInvalid() {

		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setType(null);
		});

		assertEquals("TYPE IS NULL", exp.getMessage());

	}

	@Test
	void testGetFirstName() {
		v.setFirstName(validFirstName);
		assertEquals(validFirstName, v.getFirstName());

	}

	@Test
	void testSetFirstNameInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setFirstName(null);
		});

		assertEquals("FIRST NAME IS NULL", exp.getMessage());
	}

	@Test
	void testSetGetLastName() {
		v.setLastName(validLastName);
		assertEquals(validLastName, v.getLastName());
	}

	@Test
	void testSetLastNameInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setLastName(null);
		});

		assertEquals("LAST NAME IS NULL", exp.getMessage());
	}

	@Test
	void testSetGetAge() {
		v.setAge(validage);
		assertEquals(validage, v.getAge());
	}

	@Test
	void testSetAgeInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setAge(invalidage);
		});

		assertEquals("INVALID AGE", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setAge(invalidagenegative);
		});

		assertEquals("INVALID AGE", exp.getMessage());
	}

	@Test
	void testSetGetTown() {
		v.setTown(validTown);
		assertEquals(validTown, v.getTown());
	}

	@Test
	void testSetTownInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setTown(null);
		});

		assertEquals("TOWN IS NULL", exp.getMessage());
	}

	@Test
	void testSetGetEmail() {
		v.setEmail(validEmail);
		assertEquals(validEmail, v.getEmail());
	}

	@Test
	void testSetEmailInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			v.setEmail(null);
		});

		assertEquals("EMAIL IS NULL", exp.getMessage());
	}

}
