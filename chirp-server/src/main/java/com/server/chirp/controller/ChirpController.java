package com.server.chirp.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.server.chirp.model.Chirp;
import com.server.chirp.service.ChirpService;

import spark.ResponseTransformer;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChirpController {
	Map<String, String> chirpMap;
	Gson gson;
	// HTTP verb -> REST meaning
	// GET -> read
	// POST -> create
	// PUT -> update
	// DELETE -> delete

	public ChirpController(ChirpService service) {
		chirpMap = new HashMap<>();
		gson = new GsonBuilder().setLenient().setDateFormat("EEE, dd/MM/yyyy").create();
		get("/chirps", (req, res) -> {
			if(service.getChirps() == null) {
				halt(404, "Chirps not found");
				return null;
			}
			return service.getChirps();
		}, json());
		
		get("/chirps/fm/:message", (req, res) -> {
			if(service.findChirpsByMessage(req.params("message")) == null) {
				halt(404, "Chirps not found");
				return null;
			}
			return service.findChirpsByMessage(req.params("message"));
		}, json());
		
		get("/chirps/fi/:id", (req, res) -> {
			if(service.findChirpsByUser(req.params("id")) == null) {
				halt(404, "Chirps not found");
				return null;
			}
			return service.findChirpsByUser(req.params("id"));
		}, json());
		
		get("/chirps/fd/:date", (req, res) -> {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(req.params("date"));
			if(service.findChirpsByDate(date) == null) {
				halt(404, "Chirps not found");
				return null;
			}
			return service.findChirpsByDate(date);
		}, json());
		
		get("/chirps/fh/:handle", (req, res) -> {
			if(service.findChirpsByUser(req.params("handle")) == null) {
				halt(404, "Chirps not found");
				return null;
			}
			return service.findChirpsContainingUser(req.params("handle"));
		}, json());
		
		post("/chirps/a", (req, res) -> {
			chirpMap.clear();
			ChirpTransport chirp = gson.fromJson(req.body(), ChirpTransport.class);
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(chirp.getDate());
			chirpMap.put("id", chirp.getUserId());
			chirpMap.put("chirp_created", "true");
			chirpMap.put("message", chirp.getMessage());
			chirpMap.put("date", chirp.getDate().toString());
			service.addChirp(chirp.getMessage(), date, chirp.getUserId());
			return chirpMap;
		}, json());
	}

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static ResponseTransformer json() {
		return UserController::toJson;
	}
	
	public class ChirpTransport {
		private String message;
		private String date;
		private String userId;
		
		public String getMessage() {return message;}
		public String getDate() {return date;}
		public String getUserId() {return userId;}
	}

}
