package com.server.chirp.model;

import java.time.ZonedDateTime;

public class Chirp {
	private String message;
	private ZonedDateTime date;
	
	
	//default constructor needed for Gson
	private Chirp() {}
	
	public Chirp(String message, ZonedDateTime date) {
		this.message = message;
		this.date = date;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setDate(ZonedDateTime date) {
		this.date = date;
	}
	
	public String getMessage() {
		return message;
	}
	
	public ZonedDateTime getDate() {
		return date;
	}
}
