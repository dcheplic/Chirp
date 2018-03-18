package com.server.chirp.util;

public class DuplicateEmailException extends UserAppException {
	private static final long serialVersionUID = 1L;
	
	public DuplicateEmailException(String message) {
		super(message);
	}
	
	public DuplicateEmailException(String message, Throwable cause) {
		super(message,cause);
	}

}
