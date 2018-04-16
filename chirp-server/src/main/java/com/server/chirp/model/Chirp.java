package com.server.chirp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;

public class Chirp {
	private String message;
	private Date date;
	private String userId;
	private String id;
	
	//default constructor needed for Gson
	private Chirp() {}
	
	public Chirp(String message, Date date, String userId) {
		this.message = message;
		this.date = date;
		this.userId = userId;
		id = UUID.randomUUID().toString();
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
	
	public static Chirp fromItem(Item item) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(item.getString("date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Chirp(item.getString("message"), date, item.getString("id"));
	}
	
	public void fillItem(Item item) {
		item.withPrimaryKey("id", getUserId())
		.withString("message", getMessage())
		.with("date", getDate().toString());
	}
	
	public String getId() {
		return id;
	}
}
