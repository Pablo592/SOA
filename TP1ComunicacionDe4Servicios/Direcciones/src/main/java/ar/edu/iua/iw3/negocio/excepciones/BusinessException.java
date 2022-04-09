package ar.edu.iua.iw3.negocio.excepciones;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 5472509512704239504L;

	public BusinessException() {
	
	}

	public BusinessException(String message) {
		super(message);
		
	}

	public BusinessException(Throwable cause) {
		super(cause);
		
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
