package com.server.chirp.tests;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.server.chirp.model.Chirp;
import com.server.chirp.model.User;
import com.server.chirp.service.UserService;
import com.server.chirp.service.impl.UserServiceImpl;
import com.server.chirp.storage.UserStorage;
import com.server.chirp.util.StorageException;
import com.server.chirp.util.UserAppException;
import com.server.chirp.storage.impl.InMemoryUserStorage;;

public class ServiceTests {
	public UserService service;
	public UserStorage storage;
	public User u1;
	public User u2;
	public User u3;
	public User u4;
	public User u5;
	public ArrayList<Chirp> list;

	@Before
	public void setUp() throws StorageException {
		storage = new InMemoryUserStorage();
		list = new ArrayList<>();
		Chirp c1 = new Chirp("Hello!", ZonedDateTime.now());
		list.add(c1);
		u1 = new User("Bob", "palindrome@gmail.com", "pass", "boob", UUID.randomUUID(), list);
		u2 = new User("Jim", "supervisor@sunnyvale.org", "word", "drnk", UUID.randomUUID(), list);
		u3 = new User("Bill", "will@yahoo.com", "billiam","bwiillll", UUID.randomUUID(), list);
		u4 = new User("Barb","email@gmail.com", "wordpass","barbie", UUID.randomUUID(), list);
		u5 = new User("Jim", "hacksaw@comcast.net", "wasspord","HacksawJimDugan", UUID.randomUUID(), list);
		service = new UserServiceImpl(storage);
	}
	
	@Test
	public void createUser_works() throws UserAppException{
		service.createUser(u1.getName(), u1.getEmail(), u1.getPassword(), u1.getHandle(), list);
		assertEquals(u1.getHandle(), service.findUserByEmail("palindrome@gmail.com").getHandle());
	}

}
