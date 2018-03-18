package com.server.chirp.service;

import java.util.List;
import java.util.UUID;

import javax.swing.text.AttributeSet.CharacterAttribute;

import com.server.chirp.model.User;
import com.server.chirp.util.UserAppException;

public interface UserService {
	public List<User> getUsers() throws UserAppException; //will return list of all users
	public User findUserById(UUID id) throws UserAppException; //will return user based on id
	public List<User> findUserByName(String name) throws UserAppException; //will return list of all users named "name"
	public User findUserByEmail(String email) throws UserAppException; //will return user based on email
	public User findUserByHandle(String handle) throws UserAppException; //will return user based on handle
	public void createUser(String name, String email, String handle) throws UserAppException; //will create new user
	public void updateUser(UUID id, String name, String email, String handle) throws UserAppException; //will update user with id "id"
	public void deleteUser(UUID id) throws UserAppException; //will delete user with id "id"
}
