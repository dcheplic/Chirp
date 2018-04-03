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

	public UserController(UserService service) {
		get("/users",(req, res) -> {
			if(service.getUsers() == null)
				halt(404, "Not found");
			return service.getUsers();
		}, json());

		get("/users/:id", (req, res) -> {
			if(service.findUserById(UUID.fromString(req.attribute(":id"))) == null)
				halt(404, "Not found");
			return service.findUserById(UUID.fromString(req.attribute(":id")));
		}, json());	

		get("/users/:email", (req, res) -> {
			if(service.findUserByEmail(req.attribute(":email")) == null)
				halt(404, "Not found");
			return service.findUserByEmail(req.attribute(":email"));
		}, json());

		get("/users/:handle", (req, res) -> {
			if(service.findUserByHandle(req.attribute(":handle")) == null)
				halt(404, "Not found");
			return service.findUserByHandle(req.attribute(":handle"));
		}, json());

		get("/users/:name", (req, res) -> {
			if(service.findUserByName(req.attribute(":name")) == null)
				halt(404, "Not found");
			return service.findUserByName(req.attribute(":name"));
		}, json());

		put("/users", (req, res) -> {
			String name = req.params("name");
			String email = req.params("email");
			String password = req.params("password");
			String handle = req.params("handle");
			service.createUser(name, email, password, handle, new ArrayList<Chirp>());
			return "User created";
		}, json());	

		post("/users/:id", (req, res) -> {
			if(service.findUserById(UUID.fromString(req.attribute(":id"))) == null)
				halt(404, "Not found");
			UUID id = UUID.fromString(req.attribute(":id"));
			String name = req.params("name");
			String email = req.params("email");
			String password = req.params("password");
			String handle = req.params("handle");
			service.updateUser(id, name, email, handle);
			service.updatePassword(id, password);
			return "User updated";
		}, json());	

		delete("/users/:id", (req, res) -> {
			if(service.findUserById(UUID.fromString(req.attribute(":id"))) == null)
				halt(404, "Not found");
			service.deleteUser(UUID.fromString(req.attribute(":id")));
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
