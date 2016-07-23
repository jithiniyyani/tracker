package com.stolenvehicle.exception;


/**
 * Class SystemException.
 *
 * 
 * @version 1.0
 * Class represents SystemException (Wrapper for RuntimeException)
 */
public class SystemException extends RuntimeException {

	/**
	 * The Constant serialVersionUID.
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new system exception.
	 */
	public SystemException() {
		super();
	
	}

	/**
	 * Instantiates a new system exception.
	 *
	 * @param message message 
	 * @param cause  cause
	 * @param enableSuppression enable suppression
	 * @param writableStackTrace   writable stack trace 
	 */
	public SystemException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	/**
	 * Instantiates a new system exception.
	 *
	 * @param message  message 
	 * @param cause    cause 
	 */
	public SystemException(final String message, final Throwable cause) {
		super(message, cause);
	
	}

	/**
	 * Instantiates a new system exception.
	 *
	 * @param message   message 
	 */
	public SystemException(final String message) {
		super(message);		
	}

	/**
	 * Instantiates a new system exception.
	 *
	 * @param cause
	 *            cause @todo{Add description}
	 */
	public SystemException(final Throwable cause) {
		super(cause);		
	}
	
	

}
