package com.server.chirp.storage;

import java.util.List;

import com.server.chirp.model.User;
import com.server.chirp.util.StorageException;

public interface UserStorage {
	
	public List<User> getUsers() throws StorageException; //will return list of all users
	public User findUserById(String id) throws StorageException; //will return user based on id
	public List<User> findUserByName(String name) throws StorageException; //will return list of all users named "name"
	public User findUserByEmail(String email) throws StorageException; //will return user based on email
	public User findUserByHandle(String handle) throws StorageException; //will return user based on handle
	public void addUser(User u) throws StorageException; //will create new user
	public void updateUser(String id, String name, String email, String handle) throws StorageException; //will update user with id "id"
	public void updatePassword(String id, String password); //updates user based on id
	public void deleteUser(String id) throws StorageException; //will delete user with id "id"

}
