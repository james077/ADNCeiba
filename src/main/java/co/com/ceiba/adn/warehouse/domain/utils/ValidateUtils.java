package co.com.ceiba.adn.warehouse.domain.utils;

import java.util.Date;

import co.com.ceiba.adn.warehouse.domain.exception.RequiredValueException;
import co.com.ceiba.adn.warehouse.domain.exception.WarehouseException;

public class ValidateUtils {

   private static final String FECHAS_NO_PUEDEN_SER_NULL="Las fechas no pueden ser nulas";
	
	public void validateObligatory(Object valor, String msg) {
        if (valor == null) {
            throw new RequiredValueException(msg);
        }
    }
    
    public int calculateDifDates(Date iniDate, Date finDate) {
    	if(iniDate != null && finDate != null) {
	    	int milliseconds = 60*60*24*1000;
			int daysIniDate= (int) (iniDate.getTime()/milliseconds);
	    	int daysFinDate =(int)(finDate.getTime()/milliseconds);
			return daysFinDate - daysIniDate;
    	}else {
    		throw new WarehouseException(FECHAS_NO_PUEDEN_SER_NULL);
    	}
    }
    
}
