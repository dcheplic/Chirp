package com.server.chirp.util;

public class UserAppException extends CosmicRayException {
	private static final long serialVersionUID = 1L;

	public UserAppException(String message) {
		super(message);
	}
	
	public UserAppException(String message, Throwable cause) {
		super(message, cause);
	}

}
