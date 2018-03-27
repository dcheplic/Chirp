package com.server.chirp.controller;

import com.google.gson.Gson;
import com.server.chirp.service.UserService;

import spark.ResponseTransformer;
import static spark.Spark.get;

import java.util.UUID;

public class UserController {
	
	// HTTP verb -> REST meaning
	// GET -> read
	// PUT -> create
	// POST -> update
	// DELETE -> delete
	
	public UserController(UserService service) {
		get("/users",(req, res) -> {
			return service.getUsers();
		});
		
		get("/users/:id", (req, res) -> {
			return service.findUserById(UUID.fromString(req.attribute(":id")));
		}, json());	
		
		get("/users/:email", (req, res) -> {
			return service.findUserByEmail(req.attribute(":email"));
		}, json());
		
		get("/users/:handle", (req, res) -> {
			return service.findUserByHandle(req.attribute(":handle"));
		}, json());
		
		get("/users/:name", (req, res) -> {
			return service.findUserByName(":name");
		}, json());
		
	}

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}
	
	public static ResponseTransformer json() {
		return UserController::toJson;
	}
}
