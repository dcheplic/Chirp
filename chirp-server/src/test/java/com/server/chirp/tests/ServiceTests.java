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
	public void setUp() throws StorageException, UserAppException {
		storage = new InMemoryUserStorage();
		list = new ArrayList<>();
		Chirp c1 = new Chirp("Hello!", ZonedDateTime.now());
		list.add(c1);
		service = new UserServiceImpl(storage);
		service.createUser("Bob", "palindrome@gmail.com", "pass", "boob", list);
		service.createUser("Jim", "supervisor@sunnyvale.org", "word", "drnk", list);
		service.createUser("Bill", "will@yahoo.com", "billiam","bwiillll", list);
		service.createUser("Barb","email@gmail.com", "wordpass","barbie", list);
		service.createUser("Jim", "hacksaw@comcast.net", "wasspord","HacksawJimDugan", list);
	}
	
	@Test
	public void getUsers_works() throws UserAppException {
		assertEquals(5, service.getUsers().size());
	}
	
	@Test
	public void findUserByEmail_works() throws UserAppException {
		assertEquals("Jim", service.findUserByEmail("supervisor@sunnyvale.org").getName());
	}
	
	@Test
	public void findUserByHandle_works() throws UserAppException {
		assertEquals("Bill", service.findUserByHandle("bwiillll").getName());
	}
	
	@Test
	public void findUserById_works() throws UserAppException {
		UUID id = service.getUsers().get(2).getId();
		assertEquals("Bill", service.findUserById(id).getName());
	}
	
	@Test
	public void createUser_works() throws UserAppException {
		service.createUser("John", "beatles@rock.roll", "ono", "1M4G1N3", list);
		assertEquals("John", service.findUserByHandle("1M4G1N3").getName());
		assertEquals(6, service.getUsers().size());
	}
	
	@Test
	public void updateUser_works() throws UserAppException {
		UUID id = service.getUsers().get(2).getId();
		service.updateUser(id, "Link", "triforce@hyrule.com", "LegendaryHero");
		assertEquals("Link", service.findUserById(id).getName());
	}
	
	@Test
	public void updatePassword_works() throws UserAppException {
		UUID id = service.getUsers().get(2).getId();
		service.updatePassword(id, "words");
		assertEquals("words", service.findUserById(id).getPassword());
	}
	
	@Test
	public void deleteUser_works() throws UserAppException {
		UUID id = service.getUsers().get(2).getId();
		service.deleteUser(id);
		assertEquals(4, service.getUsers().size());
	}
	
	

}
