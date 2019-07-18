/**
 * 
 */
package com.abc.cricket.service;

import com.abc.cricket.ApplicationException;

/**
 * @author Soham Chakravarti
 *
 */
public class ModelNotFoundException extends ApplicationException {


	private static final long serialVersionUID = 1L;

	public ModelNotFoundException() {
	}

	public ModelNotFoundException(String message) {
		super(message);
	}

	public ModelNotFoundException(Throwable cause) {
		super(cause);
	}

	public ModelNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
