package com.server.chirp.service.impl;

import java.util.List;
import java.util.UUID;

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
	public User findUserById(long id) throws UserAppException {
		return storage.findUserById("" + id);
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
	public void createUser(String email, String password, String handle) throws UserAppException {
		User u = new User(email, password, handle, UUID.randomUUID().getMostSignificantBits() &Long.MAX_VALUE);
		storage.addUser(u);
		
	}

	@Override
	public void updateUser(long id, String email, String handle) throws UserAppException {
		storage.updateUser("" + id, email, handle);
		
	}
	
	@Override
	public void updatePassword(long id, String password) {
		storage.updatePassword("" + id, password);
		
	}

	@Override
	public void deleteUser(long id) throws UserAppException {
		storage.deleteUser("" + id);
	}

}
