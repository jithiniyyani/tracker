package com.stolenvehicle.exception;



/**
 * Class BusinessException.
 *
 * 
 * @version 1.0 Class represent BuinsessException
 */
public class BusinessException extends Exception {

	/**
	 * The Constant serialVersionUID.
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new business exception.
	 */
	public BusinessException() {
		super();
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param message
	 *            message
	 * @param cause
	 *            cause
	 * @param enableSuppression
	 *            enable suppression
	 * @param writableStackTrace
	 *            writable stack trace
	 */
	public BusinessException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param message
	 *            message
	 * @param cause
	 *            cause
	 */
	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param message
	 *            message
	 */
	public BusinessException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param cause
	 *            cause
	 */
	public BusinessException(final Throwable cause) {
		super(cause);

	}

}
