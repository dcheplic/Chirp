package com.server.chirp.util;

public class CosmicRayException extends Exception {
	private static final long serialVersionUID = 1L;

	public CosmicRayException(String message) {
		super(message);
	}

	public CosmicRayException(String message, Throwable cause) {
		super(message, cause);
	}
}
