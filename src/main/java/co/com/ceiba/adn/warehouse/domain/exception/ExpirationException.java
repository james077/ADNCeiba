package co.com.ceiba.adn.warehouse.domain.exception;

public class ExpirationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public ExpirationException(String message) {
        super(message);
    }
}
