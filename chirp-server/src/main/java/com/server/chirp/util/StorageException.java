package com.server.chirp.util;

public class StorageException extends UserAppException {
	private static final long serialVersionUID = 1L;

	public StorageException(String message) {
		super(message);
	}
	
	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}

}
