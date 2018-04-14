package com.server.chirp.main;
import static spark.Spark.port;

import org.apache.log4j.BasicConfigurator;

import com.server.chirp.controller.ChirpController;
import com.server.chirp.controller.UserController;
import com.server.chirp.service.impl.ChirpServiceImpl;
import com.server.chirp.service.impl.UserServiceImpl;
import com.server.chirp.storage.impl.InMemoryChirpStorage;
import com.server.chirp.storage.impl.InMemoryUserStorage;

public class App {
	public static void main(String[] args) {
		port(5000);
		BasicConfigurator.configure();
		new UserController(new UserServiceImpl(new InMemoryUserStorage()));
		new ChirpController(new ChirpServiceImpl(new InMemoryChirpStorage()));
	}
}
