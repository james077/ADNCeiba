package co.com.ceiba.adn.warehouse.domain.exception;

public class NonWorkingDayException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public NonWorkingDayException(String message) {
        super(message);
    }
}
