package ar.edu.iua.iw3.negocio.excepciones;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 5472509512704239504L;

	public NotFoundException() {
	
	}

	public NotFoundException(String message) {
		super(message);
		
	}

	public NotFoundException(Throwable cause) {
		super(cause);
		
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
