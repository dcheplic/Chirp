package com.server.chirp.util;

public class DuplicateHandleException extends UserAppException {
	private static final long serialVersionUID = 1L;
	
	public DuplicateHandleException(String message) {
		super(message);
	}
	
	public DuplicateHandleException(String message, Throwable cause) {
		super(message, cause);
	}
}
