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
	public ArrayList<Chirp> list;

	@Before
	public void setUp() throws StorageException {
		list = new ArrayList<>();
		Chirp c1 = new Chirp("Hello!", ZonedDateTime.now());
		list.add(c1);
		u1 = new User("Bob", "palindrome@gmail.com", "pass", "boob", UUID.randomUUID());
		u2 = new User("Jim", "supervisor@sunnyvale.org", "word", "drnk", UUID.randomUUID());
		u3 = new User("Bill", "will@yahoo.com", "billiam","bwiillll", UUID.randomUUID());
		u4 = new User("Barb","email@gmail.com", "wordpass","barbie", UUID.randomUUID());
		u5 = new User("Jim", "hacksaw@comcast.net", "wasspord","HacksawJimDugan", UUID.randomUUID());
		storage = new InMemoryUserStorage();
		storage.addUser(u1);
		storage.addUser(u2);
		storage.addUser(u3);
		storage.addUser(u4);
		storage.addUser(u5);
	}

	@Test
	public void findUserByName_works() throws StorageException {
		assertEquals(u5, storage.findUserByName("Jim").get(1));
	}

	@Test
	public void findUserByEmail_works() throws StorageException {
		assertEquals(u4, storage.findUserByEmail("email@gmail.com"));
	}
	
	@Test
	public void findUserByHandle_works() throws StorageException {
		assertEquals(u3, storage.findUserByHandle("bwiillll"));
	}
	
	@Test
	public void addUser_works() throws StorageException {
		User u6 = new User("John", "beatles@yahoo.com", "ono", "Gone2Soon", UUID.randomUUID());
		storage.addUser(u6);
		assertEquals(u6, storage.findUserByHandle("Gone2Soon"));
	}
	
	@Test
	public void updateUser_works() throws StorageException {
		String uid = u3.getId().toString();
		storage.updateUser(uid, "James", "sawhack@gmail.com", "2x4");
		assertEquals(u3, storage.findUserByName("James").get(0));
		assertEquals(u3, storage.findUserByEmail("sawhack@gmail.com"));
		assertEquals(u3, storage.findUserByHandle("2x4"));
	}
	
	@Test
	public void updatePassword_works() throws StorageException {
		String uid = u3.getId().toString();
		storage.updatePassword(uid, "wham");
		assertEquals("wham", u3.getPassword());
	}
	
	@Test
	public void deleteUser_works() throws StorageException {
		String uid = u3.getId().toString();
		storage.deleteUser(uid);
		assertEquals(4, storage.getUsers().size());
	}

}
