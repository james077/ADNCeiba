package co.com.ceiba.adn.warehouse.domain;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import co.com.ceiba.adn.BasePrueba;
import co.com.ceiba.adn.warehouse.domain.exception.ExpirationException;
import co.com.ceiba.adn.warehouse.domain.exception.NonWorkingDayException;
import co.com.ceiba.adn.warehouse.domain.exception.RequiredValueException;
import co.com.ceiba.adn.warehouse.domain.model.Product;
import co.com.ceiba.adn.warehouse.testdatabuilder.ProductTestDataBuilder;

public class ProductTest {

	@Test
	public void testValidarFechaVencimientoObligatoria() {
		// Arrange
		String nameProduct = "Nuevo Producto";
		float depth = 2;
		float width = 2;
		float height = 2;

		// Act and Assert
		BasePrueba.assertThrows(() -> new Product(null, nameProduct, null,Calendar.getInstance(),null, depth, width, height),
				RequiredValueException.class, "La fecha de vencimiento es un campo obligatorio");
	}

	@Test
	public void testValidarFechaIngresoFestivo() {
		// Arrange
		Product p = new ProductTestDataBuilder().build();
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 0, 5, 0, 0, 0); // Domingo 5 de enero de 2020
		p.setDateIn(cal);

		// Act and assert
		BasePrueba.assertThrows(() -> p.validateDateIn(), NonWorkingDayException.class,
				"Los Días Domingos no se permite el ingreso de productos");
	}

	@Test
	public void testValidarFechaIngresoDiaHabil() {
		// Arrange
		Exception ex = null;
		Product p = new ProductTestDataBuilder().build();
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 0, 3, 0, 0, 0); // viernes 3  de enero de 2020
		p.setDateIn(cal);

		// Act
		try {
			p.validateDateIn();
		} catch (Exception e) {
			ex = e;
		}

		// Assert
		Assert.assertNull(ex);
	}

	@Test
	public void testValidarFechaVencimientoInvalida() {
		// Arrange
		Calendar expirationDate = Calendar.getInstance();
		expirationDate.setTimeInMillis(expirationDate.getTimeInMillis() + (86400000 * 9));

		// Act and assert
		BasePrueba.assertThrows(() -> new Product().validateExpirationDate(expirationDate),
				ExpirationException.class, "El producto se vence en menos de 10 dias, no se puede ingresar");
	}

	@Test
	public void testValidarFechaVencimientoValida() {
		// Arrange
		Exception ex = null;
		Calendar expirationDate = Calendar.getInstance();
		expirationDate.setTimeInMillis(expirationDate.getTimeInMillis() + (86400000 * 10));

		// Act
		try {
			new Product().validateExpirationDate(expirationDate);
		} catch (Exception e) {
			ex = e;
		}

		// Assert
		Assert.assertNull(ex);
	}
	
	@Test
	public void testCreateProductDateNull() {
		// Arrange
		Exception ex = null;

		// Act
		try {
			 new Product(null,"otro Producto", null,null,null, 2, 3, 4);
		} catch (Exception e) {
			ex = e;
		}

		// Assert
		Assert.assertNotNull(ex);
	}
	
	@Test
	public void testCreateProductCorrect() {
		// Arrange
		Exception ex = null;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(cal.getTimeInMillis()+2592000000L); //fecha actual mas 30 dias
	
		// Act
		try {
			 new Product(null,"otro Producto", cal,Calendar.getInstance(),Calendar.getInstance(), 2, 3, 4);
		} catch (Exception e) {
			ex = e;
		}

		// Assert
		Assert.assertNull(ex);
	}
}
