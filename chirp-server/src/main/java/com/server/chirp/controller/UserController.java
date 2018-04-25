package com.server.chirp.controller;

import com.google.gson.Gson;
import com.server.chirp.model.User;
import com.server.chirp.service.UserService;

import spark.ResponseTransformer;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.Spark.halt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserController {
	Map<String,String> userMap;
	Gson gson;
	// HTTP verb -> REST meaning
	// GET -> read
	// POST -> create
	// PUT -> update
	// DELETE -> delete

	public UserController(UserService service) {
		gson = new Gson();
		userMap = new HashMap<>();
		Date date = new Date();
		
		//opening page
		get("/",(req, res) -> {
			return "Chirp Server: Hi, welcome to Chilis!";
		}, json());
		
		//returns list of users
		get("/users",(req, res) -> {
			if(service.getUsers() == null) {
				halt(404, "Users not found");
				return null;
			}
			return service.getUsers();
		}, json());

		//returns user based on id
		get("/users/fi/:id", (req, res) -> {
			if(service.findUserById(Long.parseLong(req.params("id"))) == null) {
				halt(404, "User not found");
				return null;
			}
			return service.findUserById(Long.parseLong(req.params("id")));
		}, json());	

		//returns users based on email
		get("/users/fe/:email", (req, res) -> {
			if(service.findUserByEmail(req.params("email")) == null) {
				halt(404, "User not found");
				return null;
			}
			return service.findUserByEmail(req.params("email"));
		}, json());

		//returns user based on handle
		get("/users/fh/:handle", (req, res) -> {
			if(service.findUserByHandle(req.params("handle")) == null) {
				halt(404, "User not found");
				return null;
			}
			return service.findUserByHandle(req.params("handle"));
		}, json());

		//creates new user
		post("/users/c", (req, res) -> {
			userMap.clear();
			User user = gson.fromJson(req.body(), User.class);
			if(service.findUserByEmail(user.getEmail()) != null) {
				halt("Email already in use");
				userMap.put("id", Long.toString(user.getId()));
				userMap.put("user_created", "false");
				return userMap;
			}
			userMap.put("id", Long.toString(user.getId()));
			userMap.put("user_created", "true");
			userMap.put("email", user.getEmail());
			userMap.put("password", user.getPassword());
			userMap.put("handle", user.getHandle());
			service.createUser(user.getEmail(), user.getPassword(), user.getHandle());
			return userMap;
		}, json());	
		
		// TODO possibly flesh out update methods

		//updates user with provided information
		put("/users/u/:id", (req, res) -> {
			userMap.clear();
			if(service.findUserById(Long.parseLong(req.params("id"))) == null) {
				halt(404, "User not found");
				return null;
			}
			long id = Long.parseLong(req.params("id"));
			User user = gson.fromJson(req.body(), User.class);
			userMap.put("id", Long.toString(user.getId()));
			userMap.put("user_updated", "true");
			userMap.put("email", user.getEmail());
			userMap.put("password", user.getPassword());
			userMap.put("handle", user.getHandle());
			service.updateUser(id, user.getEmail(), user.getHandle());
			return userMap;
		}, json());	

		//updates user password based on user id
		put("/users/pu/:id", (req, res) -> {
			userMap.clear();
			if(service.findUserById(Long.parseLong(req.params("id"))) == null) {
				halt(404, "User not found");
				return null;
			}
			long id = Long.parseLong(req.params("id"));
			User user = gson.fromJson(req.body(), User.class);
			userMap.put("id", Long.toString(user.getId()));
			userMap.put("user_updated", "true");
			userMap.put("email", user.getEmail());
			userMap.put("password", user.getPassword());
			userMap.put("handle", user.getHandle());
			service.updatePassword(id, user.getPassword());
			return userMap;
		}, json());	

		//deletes user based on id
		delete("/users/d/:id", (req, res) -> {
			userMap.clear();
			if(service.findUserById(Long.parseLong(req.params("id"))) == null) {
				halt(404, "User not found");
				return null;
			}
			userMap.put("id", req.params("id"));
			userMap.put("user_deleted", "true");
			service.deleteUser(Long.parseLong(req.params("id")));
			return userMap;
		}, json());	
	}

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static ResponseTransformer json() {
		return UserController::toJson;
	}
}
