package com.server.chirp.service.impl;

import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.server.chirp.model.Chirp;
import com.server.chirp.model.User;
import com.server.chirp.service.UserService;
import com.server.chirp.storage.UserStorage;
import com.server.chirp.util.UserAppException;

public class UserServiceImpl implements UserService{

	private UserStorage storage;
	
	public UserServiceImpl(UserStorage storage) {
		this.storage = storage;
	}
	@Override
	public List<User> getUsers() throws UserAppException {
		return storage.getUsers();
	}

	@Override
	public User findUserById(UUID id) throws UserAppException {
		return storage.findUserById(id.toString());
	}

	@Override
	public List<User> findUserByName(String name) throws UserAppException {
		return storage.findUserByName(name);
	}

	@Override
	public User findUserByEmail(String email) throws UserAppException {
		return storage.findUserByEmail(email);
	}

	@Override
	public User findUserByHandle(String handle) throws UserAppException {
		return storage.findUserByHandle(handle);
	}

	@Override
	public void createUser(String name, String email, String password, String handle, ArrayList<Chirp> chirpList) throws UserAppException {
		User u = new User(name, email, password, handle, UUID.randomUUID(), chirpList);
		storage.addUser(u);
		
	}

	@Override
	public void updateUser(UUID id, String name, String email, String handle) throws UserAppException {
		storage.updateUser(id.toString(), name, email, handle);
		
	}
	
	@Override
	public void updatePassword(UUID id, String password) {
		storage.updatePassword(id.toString(), password);
		
	}

	@Override
	public void deleteUser(UUID id) throws UserAppException {
		storage.deleteUser(id.toString());
	}

}
