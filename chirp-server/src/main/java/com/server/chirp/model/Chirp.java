package com.server.chirp.model;

import java.util.Date;
import java.util.UUID;

public class Chirp {
	private String message;
	private Date date;
	private String userId;
	
	//default constructor needed for Gson
	private Chirp() {}
	
	public Chirp(String message, Date date, String userId) {
		this.message = message;
		this.date = date;
		this.userId = userId;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getUserId() {
		return userId;
	}
}
