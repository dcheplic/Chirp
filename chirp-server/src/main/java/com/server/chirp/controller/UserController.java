package com.server.chirp.controller;

import com.google.gson.Gson;
import com.server.chirp.model.Chirp;
import com.server.chirp.model.User;
import com.server.chirp.service.UserService;

import spark.ResponseTransformer;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.Spark.halt;

import java.util.ArrayList;
import java.util.UUID;

public class UserController {
	
	// HTTP verb -> REST meaning
	// GET -> read
	// POST -> create
	// PUT -> update
	// DELETE -> delete

	//returns list of users
	public UserController(UserService service) {
		Gson gson = new Gson();
		get("/users",(req, res) -> {
			if(service.getUsers() == null) {
				halt(404, "Users not found");
				return null;
			}
			return service.getUsers();
		}, json());

		//returns user based on id
		get("/users/:id", (req, res) -> {
			if(service.findUserById(UUID.fromString(req.params("id"))) == null) {
				halt(404, "User not found");
				return null;
			}
			return service.findUserById(UUID.fromString(req.params("id")));
		}, json());	

		//returns users based on email
		get("/users/:email", (req, res) -> {
			if(service.findUserByEmail(req.params("email")) == null) {
				halt(404, "User not found");
				return null;
			}
			return service.findUserByEmail(req.params("email"));
		}, json());

		//returns user based on handle
		get("/users/:handle", (req, res) -> {
			if(service.findUserByHandle(req.params("handle")) == null) {
				halt(404, "User not found");
				return null;
			}
			return service.findUserByHandle(req.params("handle"));
		}, json());

		//returns users based on name
		get("/users/:name", (req, res) -> {
			if(service.findUserByName(req.params("name")) == null) {
				halt(404, "User not found");
				return null;
			}
			return service.findUserByName(req.params("name"));
		}, json());

		//creates new user
		post("/users/c", (req, res) -> {
			User user = gson.fromJson(req.body(), User.class);
			service.createUser(user.getName(), user.getEmail(), user.getPassword(), user.getHandle());
			return "User created";
		}, json());	
		
		// TODO possibly flesh out update methods

		//updates user with provided information
		put("/users/u/:id", (req, res) -> {
			if(service.findUserById(UUID.fromString(req.params("id"))) == null) {
				halt(404, "User not found");
				return null;
			}
			UUID id = UUID.fromString(req.params("id"));
			User user = gson.fromJson(req.body(), User.class);
			service.updateUser(id, user.getName(), user.getEmail(), user.getHandle());
			return "User updated";
		}, json());	

		//updates user password based on user id
		put("/users/pu/:id", (req, res) -> {
			if(service.findUserById(UUID.fromString(req.params("id"))) == null) {
				halt(404, "User not found");
				return null;
			}
			UUID id = UUID.fromString(req.params("id"));
			User user = gson.fromJson(req.body(), User.class);
			service.updatePassword(id, user.getPassword());
			return "User password updated";
		}, json());	

		//deletes user based on id
		delete("/users/d/:id", (req, res) -> {
			if(service.findUserById(UUID.fromString(req.params("id"))) == null) {
				halt(404, "User not found");
				return null;
			}
			service.deleteUser(UUID.fromString(req.params("id")));
			return "User deleted";
		}, json());	
	}

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static ResponseTransformer json() {
		return UserController::toJson;
	}
}
