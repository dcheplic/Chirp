package com.server.chirp.model;

import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;

public class User {
	private String name;
	private String email;
	private String password;
	private String handle;
	private UUID id;
	
	//default constructor, needed by GSON
	private User(){}
	
	public User(String name, String email, String password, String handle, UUID id) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.handle = handle;
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setHandle(String handle) {
		this.handle = handle;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getHandle() {
		return handle;
	}
	
	public UUID getId() {
		return id;
	}
	
	public static User fromItem(Item item) {
		return new User(item.getString("name"),
				item.getString("email"),
				item.getString("password"),
				item.getString("handle"),
				UUID.fromString(item.getString("id")));
	}
	
	public void fillItem(Item item) {
		item.withPrimaryKey("id", getId().toString())
		.withString("name", getName())
		.withString("email", getEmail())
		.withString("password", getPassword())
		.withString("handle", getHandle());
	}
	
}
