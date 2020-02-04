package co.com.ceiba.adn.warehouse.testdatabuilder;

import java.util.Calendar;
import java.util.Date;

import co.com.ceiba.adn.warehouse.domain.model.Product;

public class ProductTestDataBuilder {

	private String nameProduct;
	private Date expirationDate;
	private float depth;
	private float width;
	private float height;
	
	public ProductTestDataBuilder() {
		this.nameProduct = "Queso";
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(cal.getTimeInMillis()+2592000000L); //fecha actual mas 30 dias
		this.expirationDate = cal.getTime();
		this.depth = 2;
		this.width = 2;
		this.height = 2;
	}
	
	
	public ProductTestDataBuilder conFechaVencimiento(int anio, int mes, int dia) {
		if(anio==0 && mes==0 && dia==0) {
			this.expirationDate = null;
		}else {
			Calendar cal = Calendar.getInstance();
			cal.set(120, 2, 1,0,0,0);
			this.expirationDate = cal.getTime();
		}
			
		 return this;
	}
	
	public ProductTestDataBuilder conNombre(String name) {
		this.nameProduct = name;
		return this;
	}
	
	public Product build() {
		return new Product(null,nameProduct,expirationDate,new Date(),null,depth,width,height);
	}
}
