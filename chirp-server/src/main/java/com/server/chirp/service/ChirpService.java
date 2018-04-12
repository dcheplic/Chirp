package com.server.chirp.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.server.chirp.model.Chirp;
import com.server.chirp.model.User;
import com.server.chirp.util.UserAppException;

public interface ChirpService {
	public List<Chirp> getChirps() throws UserAppException; //returns list of all Chirps
	public List<Chirp> findChirpsByMessage(String message) throws UserAppException; //returns list of Chirps matching message
	public List<Chirp> findChirpsByDate(Date date) throws UserAppException; //returns list of Chirps matching date
	public List<Chirp> findChirpsByUser(UUID id) throws UserAppException; //returns list of Chirps matching user id
	public void addChirp(String message, Date date, User user) throws UserAppException; //creates new Chirp
}
