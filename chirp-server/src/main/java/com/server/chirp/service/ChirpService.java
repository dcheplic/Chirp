package com.server.chirp.service;

import java.util.Date;
import java.util.List;

import com.server.chirp.model.Chirp;
import com.server.chirp.util.UserAppException;

public interface ChirpService {
	public List<Chirp> getChirps() throws UserAppException; //returns list of all Chirps
	public List<Chirp> findChirpsByMessage(String message) throws UserAppException; //returns list of Chirps matching message
	public List<Chirp> findChirpsByDate(Date date) throws UserAppException; //returns list of Chirps matching date
	public List<Chirp> findChirpsByUser(String id) throws UserAppException; //returns list of Chirps matching user id
	public List<Chirp> findChirpsContainingUser(String handle) throws UserAppException; //returns list of Chirps containing username
	public void addChirp(String message, Date date, String userId) throws UserAppException; //creates new Chirp
}
