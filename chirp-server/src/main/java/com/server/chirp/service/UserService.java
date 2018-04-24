package com.server.chirp.service;

import java.util.List;
import java.util.UUID;
import com.server.chirp.model.User;
import com.server.chirp.util.UserAppException;

public interface UserService {
	public List<User> getUsers() throws UserAppException; //will return list of all users
	public User findUserById(long id) throws UserAppException; //will return user based on id
	public User findUserByEmail(String email) throws UserAppException; //will return user based on email
	public User findUserByHandle(String handle) throws UserAppException; //will return user based on handle
	public void createUser(String email, String password, String handle) throws UserAppException; //will create new user
	public void updateUser(long id, String email, String handle) throws UserAppException; //will update user with id "id"
	public void updatePassword(long id, String password); //Will update user password based on id
	public void deleteUser(long id) throws UserAppException; //will delete user with id "id"
}
