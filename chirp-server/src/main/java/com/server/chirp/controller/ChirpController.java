package com.server.chirp.controller;

import com.google.gson.Gson;
import com.server.chirp.model.Chirp;
import com.server.chirp.service.ChirpService;

import spark.ResponseTransformer;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;

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
				halt(404, "Chirps not found");
				return null;
			}
			return service.findChirpsByMessage(req.params("message"));
		}, json());
		
		get("/chirps/:id", (req, res) -> {
			if(service.findChirpsByUser(UUID.fromString(req.params("id"))) == null) {
				halt(404, "Chirps not found");
				return null;
			}
			return service.findChirpsByUser(UUID.fromString(req.params("id")));
		}, json());
		
		get("/chirps/:date", (req, res) -> {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(req.params("date"));
			if(service.findChirpsByDate(date) == null) {
				halt(404, "Shirps not found");
				return null;
			}
			return service.findChirpsByDate(date);
		}, json());
		
		post("/chirps/a", (req, res) -> {
			Chirp chirp = gson.fromJson(req.body(), Chirp.class);
			service.addChirp(chirp.getMessage(), chirp.getDate(), chirp.getUser());
			return "Chirp created";
		}, json());
	}

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static ResponseTransformer json() {
		return UserController::toJson;
	}

}
