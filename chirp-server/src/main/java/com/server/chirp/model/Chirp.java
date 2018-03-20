package com.server.chirp.model;

import java.util.Date;

public class Chirp {
	private String message;
	private Date date;
	
	
	//default constructor needed for Gson
	private Chirp() {}
	
	public Chirp(String message, Date date) {
		this.message = message;
		this.date = date;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Date getDate() {
		return date;
	}
}
