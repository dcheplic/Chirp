package com.server.chirp.controller;

import com.google.gson.Gson;
import com.server.chirp.service.UserService;

import spark.ResponseTransformer;
import static spark.Spark.get;

public class UserController {
	
	public UserController(UserService service) {
		get("/user",(req, res) -> {
			return service.getUsers();
		});
	}

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}
	
	public static ResponseTransformer json() {
		return UserController::toJson;
	}
}
