package com.server.chirp.tests;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.server.chirp.model.Chirp;
import com.server.chirp.model.User;

public class UserTests {
	public User user;
	
	@Before
	public void setUp() {
		ArrayList<Chirp> list = new ArrayList<>();
		user = new User("John Deer", "fakemail@gmail.com", "notreal", "UDntKnwMe", UUID.randomUUID());
	}
	
	@Test
	public void getName_returns_correct_name() {
		assertEquals("John Deer", user.getName());
	}
	
	@Test
	public void getEmail_returns_correct_email() {
		assertEquals("fakemail@gmail.com", user.getEmail());
	}
	
	@Test
	public void getHandle_returns_correct_handle() {
		assertEquals("UDntKnwMe", user.getHandle());
	}
	
	@Test
	public void setName_changes_name_correctly() {
		user.setName("Jane Doe");
		assertEquals("Jane Doe", user.getName());
	}
	
	@Test
	public void setEmail_changes_email_correctly() {
		user.setEmail("notrealmail@yahoo.com");
		assertEquals("notrealmail@yahoo.com", user.getEmail());
	}
	
	@Test
	public void setHandle_changes_handle_correctly() {
		user.setHandle("StllDntKnwMe");
		assertEquals("StllDntKnwMe", user.getHandle());
	}
	
	@Test
	public void uhh_uuid() {
		UUID id = UUID.randomUUID();
		user.setId(id);
		assertEquals(id, user.getId());
	}
}
