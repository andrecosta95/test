package org.fiveware.test.model.exception;

public class FivewareTestServiceException extends Exception {

	private static final long serialVersionUID = 6486873498753838813L;

	public FivewareTestServiceException(String message) {
		super(message);
	}
	
	public FivewareTestServiceException(Throwable cause) {
		super(cause);
	}
	
	public FivewareTestServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
