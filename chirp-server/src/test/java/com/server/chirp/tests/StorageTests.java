package com.server.chirp.tests;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.server.chirp.model.Chirp;
import com.server.chirp.model.User;
import com.server.chirp.storage.UserStorage;
import com.server.chirp.storage.impl.InMemoryUserStorage;
import com.server.chirp.util.StorageException;

public class StorageTests {
	public UserStorage storage;
	public User u1;
	public User u2;
	public User u3;
	public User u4;
	public User u5;

	@Before
	public void setUp() throws StorageException {
		ArrayList<Chirp> list = new ArrayList<>();
		Chirp c1 = new Chirp("Hello!", ZonedDateTime.now());
		list.add(c1);
		u1 = new User("Bob", "palindrome@gmail.com", "pass", "boob", UUID.randomUUID(), list);
		u2 = new User("Jim", "supervisor@sunnyvale.org", "word", "drnk", UUID.randomUUID(), list);
		u3 = new User("Bill", "will@yahoo.com", "billiam","bwiillll", UUID.randomUUID(), list);
		u4 = new User("Barb","email@gmail.com", "wordpass","barbie", UUID.randomUUID(), list);
		u5 = new User("Jim", "hacksaw@comcast.net", "wasspord","HacksawJimDugan", UUID.randomUUID(), list);
		storage = new InMemoryUserStorage();
		storage.addUser(u1);
		storage.addUser(u2);
		storage.addUser(u3);
		storage.addUser(u4);
		storage.addUser(u5);
	}

	@Test
	public void findUserByName_works() throws StorageException {
		assertEquals("supervisor@sunnyvale.org", storage.findUserByName("Jim").get(0).getEmail());
	}

	@Test
	public void findUserByEmail() throws StorageException {
		assertEquals(u4, storage.findUserByEmail("email@gmail.com"));
	}

}
