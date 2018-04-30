package com.server.chirp.storage;

import java.util.ArrayList;
import java.util.List;

import com.server.chirp.model.User;
import com.server.chirp.util.StorageException;
import com.server.chirp.util.UserAppException;

public interface UserStorage {
	
	public User[] getUsers() throws StorageException; //will return list of all users
	public User findUserById(String id) throws StorageException; //will return user based on id
	public User findUserByEmail(String email) throws StorageException; //will return user based on email
	public User findUserByHandle(String handle) throws StorageException; //will return user based on handle
	public void addUser(User u) throws StorageException; //will create new user
	public void updateUser(String id, String email, String handle) throws StorageException; //will update user with id "id"
	public void updatePassword(String id, String password); //updates user based on id
	public void deleteUser(String id) throws StorageException; //will delete user with id "id"
	public ArrayList<Long> getWatchList(String userId) throws UserAppException; //returns list of watched users
	public void addUserToWatchlist(String watcherId, String watchedId) throws UserAppException; //adds user to watchlist
}
