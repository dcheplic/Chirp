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
	// PUT -> create
	// POST -> update
	// DELETE -> delete

	//returns list of users
	public UserController(UserService service) {
		get("/users",(req, res) -> {
			if(service.getUsers() == null)
				halt(404, "Users not found");
			return service.getUsers();
		}, json());

		//returns user based on id
		get("/users/:id", (req, res) -> {
			if(service.findUserById(UUID.fromString(req.params("id"))) == null)
				halt(404, "User not found");
			return service.findUserById(UUID.fromString(req.params("id")));
		}, json());	

		//returns users based on email
		get("/users/:email", (req, res) -> {
			if(service.findUserByEmail(req.params("email")) == null)
				halt(404, "User not found");
			return service.findUserByEmail(req.params("email"));
		}, json());

		//returns user based on handle
		get("/users/:handle", (req, res) -> {
			if(service.findUserByHandle(req.params("handle")) == null)
				halt(404, "User not found");
			return service.findUserByHandle(req.params("handle"));
		}, json());

		//returns users based on name
		get("/users/:name", (req, res) -> {
			if(service.findUserByName(req.params("name")) == null)
				halt(404, "User not found");
			return service.findUserByName(req.params("name"));
		}, json());

		//creates new user
		put("/users", (req, res) -> {
			String name = req.params("name");
			String email = req.params("email");
			String password = req.params("password");
			String handle = req.params("handle");
			service.createUser(name, email, password, handle, new ArrayList<Chirp>());
			return "User created";
		}, json());	

		//updates user with provided information
		post("/users/:id", (req, res) -> {
			if(service.findUserById(UUID.fromString(req.params("id"))) == null)
				halt(404, "User not found");
			UUID id = UUID.fromString(req.params("id"));
			String name = req.params("name");
			String email = req.params("email");
			String password = req.params("password");
			String handle = req.params("handle");
			service.updateUser(id, name, email, handle);
			service.updatePassword(id, password);
			return "User updated";
		}, json());	
		
		//updates user password based on user id
		post("/users/:id", (req, res) -> {
			if(service.findUserById(UUID.fromString(req.params("id"))) == null)
				halt(404, "User not found");
			UUID id = UUID.fromString(req.params("id"));
			String password = req.params("password");
			service.updatePassword(id, password);
			return "User updated";
		}, json());	

		//deletes user based on id
		delete("/users/:id", (req, res) -> {
			if(service.findUserById(UUID.fromString(req.params("id"))) == null)
				halt(404, "User not found");
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
