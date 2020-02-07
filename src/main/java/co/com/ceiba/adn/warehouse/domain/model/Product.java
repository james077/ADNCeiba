package co.com.ceiba.adn.warehouse.domain.model;

import java.util.Calendar;

import co.com.ceiba.adn.warehouse.domain.exception.ExpirationException;
import co.com.ceiba.adn.warehouse.domain.exception.NonWorkingDayException;
import co.com.ceiba.adn.warehouse.domain.utils.ValidateUtils;

public class Product {
	
	private static final String FECHA_VENCIMIENTO_CAMPO_OBLIGATORIO = "La fecha de vencimiento es un campo obligatorio";
	private static final String DIMENSIONES_CAMPOS_OBLIGATORIOS = "La altura, ancho y largo del producto es obligatorio";
	private static final String LOS_DOMINGOS_NO_SE_PERMITE_INGRESAR = "Los Días Domingos no se permite el ingreso de productos";
	private static final String FECHA_DE_VENCIMIENTO_PROXIMA = "El producto se vence en menos de 10 dias, no se puede ingresar";
	private static final int MINIMO_DIAS_VENCIMIENTO = 10;
	
	private Integer idProduct;
	private String nameProduct;
	private Calendar expirationDate;
	private Calendar dateIn;
	private Calendar dateOut;
	private float length;
	private float width;
	private float height;
	
	public Product() {}

	public Product(Integer idProduct, String nameProduct, Calendar expirationDate,Calendar dateIn,
			 Calendar dateOut, float length, float width, float height) {
		ValidateUtils val = new ValidateUtils();
		val.validateObligatory(expirationDate, FECHA_VENCIMIENTO_CAMPO_OBLIGATORIO);
		val.validateObligatory(length, DIMENSIONES_CAMPOS_OBLIGATORIOS);
		val.validateObligatory(width, DIMENSIONES_CAMPOS_OBLIGATORIOS);
		val.validateObligatory(height, DIMENSIONES_CAMPOS_OBLIGATORIOS);
		
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		setExpirationDate(expirationDate);
		setDateIn(dateIn);
		setDateOut(dateOut);
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	public boolean validateDateIn() {		

		if(dateIn.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			throw new NonWorkingDayException(LOS_DOMINGOS_NO_SE_PERMITE_INGRESAR);
		}
		return true;
	}
	
	public boolean validateExpirationDate(Calendar expirationDate) {
		
		int days = new ValidateUtils().calculateDifDates(Calendar.getInstance(),expirationDate);
		if (days < MINIMO_DIAS_VENCIMIENTO) {
			throw new ExpirationException(FECHA_DE_VENCIMIENTO_PROXIMA);
		}
		
		return true;
	}

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public Calendar getExpirationDate() {
		return (expirationDate!=null)? (Calendar)expirationDate.clone() : null;
	}

	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = (expirationDate!=null)? (Calendar)expirationDate.clone() : null;
	}

	public Calendar getDateIn() {
		return (dateIn!=null)?(Calendar)dateIn.clone():null;
	}

	public void setDateIn(Calendar dateIn) {
		this.dateIn = (dateIn!=null)? (Calendar)dateIn.clone() : null;
	}

	public Calendar getDateOut() {
		return (dateOut != null)? (Calendar)dateOut.clone():null;
	}

	public void setDateOut(Calendar dateOut) {
		this.dateOut = (dateOut!=null)? (Calendar)dateOut.clone() : null;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
}
