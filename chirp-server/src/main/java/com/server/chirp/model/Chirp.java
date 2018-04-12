package com.server.chirp.model;

import java.time.ZonedDateTime;

public class Chirp {
	private String message;
	private ZonedDateTime date;
	private User user;
	
	//default constructor needed for Gson
	private Chirp() {}
	
	public Chirp(String message, ZonedDateTime date, User user) {
		this.message = message;
		this.date = date;
		this.user = user;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setDate(ZonedDateTime date) {
		this.date = date;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getMessage() {
		return message;
	}
	
	public ZonedDateTime getDate() {
		return date;
	}
	
	public User getUser() {
		return user;
	}
}
