package com.server.chirp.main;
import static spark.Spark.port;

import java.util.UUID;

import org.apache.log4j.BasicConfigurator;

import com.google.gson.Gson;
import com.server.chirp.controller.UserController;
import com.server.chirp.model.User;
import com.server.chirp.service.impl.UserServiceImpl;
import com.server.chirp.storage.impl.InMemoryUserStorage;

public class App {
	public static void main(String[] args) {
		port(5000);
		BasicConfigurator.configure();
		new UserController(new UserServiceImpl(new InMemoryUserStorage()));
		
		//System.out.println(UUID.randomUUID());
	}
	
//	public static void main(String[] args) {
//		User u = new User("Bill", "bill@comcast.net", "schlick", "will", UUID.randomUUID());
//		Gson gson = new Gson();
//		String s = gson.toJson(u);
//		System.out.println(s);
//		User u2 = gson.fromJson(s, User.class);
//		System.out.println(u2);
//	}
}
