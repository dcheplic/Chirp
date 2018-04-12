package com.server.chirp.model;

import java.util.Date;

public class Chirp {
	private String message;
	private Date date;
	private User user;
	
	//default constructor needed for Gson
	private Chirp() {}
	
	public Chirp(String message, Date date, User user) {
		this.message = message;
		this.date = date;
		this.user = user;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Date getDate() {
		return date;
	}
	
	public User getUser() {
		return user;
	}
}
