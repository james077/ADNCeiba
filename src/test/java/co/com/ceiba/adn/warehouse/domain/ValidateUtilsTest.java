package co.com.ceiba.adn.warehouse.domain;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.com.ceiba.adn.BasePrueba;
import co.com.ceiba.adn.warehouse.domain.exception.RequiredValueException;
import co.com.ceiba.adn.warehouse.domain.exception.WarehouseException;
import co.com.ceiba.adn.warehouse.domain.utils.ValidateUtils;

public class ValidateUtilsTest {

	private String msg;
	private ValidateUtils val;

	@Before
	public void init() {
		val = new ValidateUtils();
	}

	@Test 
	public void testValidateObligatoryNotNull() {
		// Arrange
		Object o = "cadena";
		Exception ex = null;
		msg = "La fecha de vencimiento es un campo obligatorio";

		// act 
		try {
			val.validateObligatory(o, msg);
		}catch(Exception e) {
			ex = e;
		}	
		
		//Assert
		Assert.assertNull(ex);
		
	}

	@Test
	public void testValidateObligatoryNull() {
		// Arrange
		Object o = null;
		msg = "La fecha de vencimiento es un campo obligatorio";

		// act - Assert
		BasePrueba.assertThrows(() -> val.validateObligatory(o, msg), RequiredValueException.class, msg);
	}
	
	@Test
	public void testCalculateDifDates() {
		//Arrange
		Calendar iniDate = Calendar.getInstance();
		iniDate.set(2020, 0, 3, 0, 0, 0); 

		Calendar finDate = Calendar.getInstance();
		finDate.set(2020, 0, 4, 0, 0, 0);
		int days;
		
		//Act
		days = val.calculateDifDates(iniDate, finDate);
		
		//Asserts
		Assert.assertTrue(days == 1);		
	}

	@Test
	public void testCalculateDifDatesNull() {
		//Arrange
		Calendar iniDate = null;
		Calendar finDate = null;
		msg="Las fechas no pueden ser nulas";
		
		//Act and Assert
		BasePrueba.assertThrows(() -> val.calculateDifDates(iniDate, finDate), WarehouseException.class, msg);
	}
}
