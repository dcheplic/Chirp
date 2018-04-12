package com.server.chirp.controller;

import com.google.gson.Gson;
import com.server.chirp.service.ChirpService;

import spark.ResponseTransformer;
import static spark.Spark.get;
import static spark.Spark.halt;

import java.time.ZonedDateTime;

public class ChirpController {

	// HTTP verb -> REST meaning
	// GET -> read
	// POST -> create
	// PUT -> update
	// DELETE -> delete

	public ChirpController(ChirpService service) {
		Gson gson = new Gson();
		get("/chirps", (req, res) -> {
			if(service.getChirps() == null) {
				halt(404, "Chirps not found");
				return null;
			}
			return service.getChirps();
		}, json());
		
		get("/chirps/:message", (req, res) -> {
			if(service.findChirpsByMessage(req.params("message")) == null) {
				halt(404, "Chirp not found");
				return null;
			}
			return service.findChirpsByMessage(req.params("message"));
		},json());

	}

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static ResponseTransformer json() {
		return UserController::toJson;
	}

}
