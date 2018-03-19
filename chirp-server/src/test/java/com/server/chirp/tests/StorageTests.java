package com.server.chirp.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

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
	public void setUp() {
		u1 = new User("Bob", "palindrome@gmail.com", "boob", UUID.randomUUID());
		u2 = new User("Jim", "supervisor@sunnyvale.org", "drnk", UUID.randomUUID());
		u3 = new User("Bill", "will@yahoo.com", "bwiillll", UUID.randomUUID());
		u4 = new User("Barb","email@gmail.com","barbie", UUID.randomUUID());
		u5 = new User("Jim", "hacksaw@comcast.net", "HacksawJimDugan", UUID.randomUUID());
		storage = new InMemoryUserStorage();
		try {
			storage.addUser(u1);
			storage.addUser(u2);
			storage.addUser(u3);
			storage.addUser(u4);
			storage.addUser(u5);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		List<User> jims = new ArrayList<>();
		jims.add(u2);
		jims.add(u5);
		try {
			assertEquals(jims, storage.findUserByName("Jim"));
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}

}
