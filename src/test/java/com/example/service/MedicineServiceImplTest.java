package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.entity.Medicine;
import com.example.exception.MedicineNotFoundException;

public class MedicineServiceImplTest {

	Medicine medicine;

	Map<Integer, Medicine> medicines = new HashMap<>();

	MedicineService medicineService = new MedicineServiceImpl();

	@BeforeEach
	void setup() {
		medicines.put(1, new Medicine(1, "Dolo 650", 10.25, "Apollo Pharmacy", true));
		medicines.put(2, new Medicine(2, "Aceclofenac", 92.41, "Dr Reddy", true));
		medicines.put(3, new Medicine(3, "Diclofenac", 32.34, "Cipla", false));
		medicines.put(4, new Medicine(4, "Cetrizine", 43.21, "Pfizer", true));
		medicines.put(5, new Medicine(5, "pantop", 61.93, "Novartis", false));
		medicines.put(6, new Medicine(6, "Ascoril D", 155.55, "Sanofi Pharmacy", true));
		medicines.put(7, new Medicine(7, "Aceclofenac", 88.41, "Cipla", true));
	}

	@Test
	public void getMedicine() throws MedicineNotFoundException {

		int id = 1;
		Medicine medicineExcpected = medicines.get(id);
		Medicine medicineActual = medicineService.getMedicine(id);
		Assertions.assertEquals(medicineExcpected, medicineActual);

	}

	@Test
	public void getMedicineNegative() throws MedicineNotFoundException {

		int id = -1;
		Medicine medicineExcpected=null;
		Medicine medicineActual = medicineService.getMedicine(id);
		Assertions.assertEquals(medicineExcpected, medicineActual);

	}

	@Test
	public void isAvailableForTrue() throws MedicineNotFoundException {
		int id=1;
		boolean expected = medicines.get(id).isAvailability();
		boolean actual = medicineService.isAvailable(id);
		Assertions.assertEquals(expected, actual);

	}

	@Test
	public void isAvailableForFalse() throws MedicineNotFoundException {
		int id = 3;
		boolean expected = medicines.get(id).isAvailability();
		boolean actual = medicineService.isAvailable(id);
		Assertions.assertEquals(expected, actual);

	}

	@Test
	public void isAvailableNegative() throws MedicineNotFoundException {
		int id = -1;
		Assertions.assertEquals(null, medicineService.isAvailable(id));

	}

	@Test
	public void manufacturers() throws MedicineNotFoundException {

		List<String> expectedManufacturers = new ArrayList<>();
		expectedManufacturers.add("Dr Reddy");
		expectedManufacturers.add("Cipla");
		List<String> actualManufacturers = medicineService.manufacturers("Aceclofenac");
		Assertions.assertEquals(expectedManufacturers, actualManufacturers);

	}

	@Test
	public void manufacturersNegative() throws MedicineNotFoundException {

		List<String> expectedManufacturers = null;
		List<String> actualManufacturers = medicineService.manufacturers("Aceclofena");
		Assertions.assertEquals(expectedManufacturers, actualManufacturers);

	}

	@Test
	public void getLowestPriceByManufacturer() throws MedicineNotFoundException {
		String expectedManufaturer="Cipla";
		String actualManufacturer = medicineService.getLowestPriceByManufacturer("Aceclofenac");
		Assertions.assertEquals(expectedManufaturer, actualManufacturer);
	}

	@Test
	public void getLowestPriceByManufacturerNegative() throws MedicineNotFoundException {
		String expectedManufaturer="No manufacturers found";
		String actualManufacturer = medicineService.getLowestPriceByManufacturer("Aceclofena");
		Assertions.assertEquals(expectedManufaturer, actualManufacturer);
	}

}
