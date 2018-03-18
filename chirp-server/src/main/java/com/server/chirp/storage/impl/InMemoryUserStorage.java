package com.server.chirp.storage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.server.chirp.model.User;
import com.server.chirp.storage.UserStorage;
import com.server.chirp.util.StorageException;

public class InMemoryUserStorage implements UserStorage{
	
	Map<String, User> users;
	
	public InMemoryUserStorage() {
		users = new HashMap<>();
	}

	@Override
	public List<User> getUsers() throws StorageException {
		return new ArrayList<User>(users.values());
	}

	@Override
	public User findUserById(String id) throws StorageException {
		return users.get(id);
	}

	@Override
	public List<User> findUserByName(String name) throws StorageException {
		List<User> result = new ArrayList<User>();
		for(User u : users.values())
			if(u.getName().equals(name))
				result.add(u);
		return result;
	}

	@Override
	public User findUserByEmail(String email) throws StorageException {
		for(User u : users.values())
			if(u.getEmail().equals(email))
				return u;
		return null;
	}

	@Override
	public User findUserByHandle(String handle) throws StorageException {
		for(User u : users.values())
			if(u.getHandle().equals(handle))
				return u;
		return null;
	}

	@Override
	public void addUser(User user) throws StorageException {
		users.put(user.getId().toString(), user);
	}

	@Override
	public void updateUser(String id, String name, String email, String handle) throws StorageException {
		users.get(id).setName(name);
		users.get(id).setEmail(email);
		users.get(id).setHandle(handle);
	}

	@Override
	public void deleteUser(String id) throws StorageException {
		users.remove(id);
	}

}
