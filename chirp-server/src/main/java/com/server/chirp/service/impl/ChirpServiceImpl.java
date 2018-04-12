package com.server.chirp.service.impl;

import java.time.ZonedDateTime;
import java.util.List;

import com.server.chirp.model.Chirp;
import com.server.chirp.model.User;
import com.server.chirp.service.ChirpService;
import com.server.chirp.storage.ChirpStorage;
import com.server.chirp.util.UserAppException;

public class ChirpServiceImpl implements ChirpService{
	
	private ChirpStorage storage;
	
	public ChirpServiceImpl(ChirpStorage storage) {
		this.storage = storage;
	}

	@Override
	public List<Chirp> getChirps() throws UserAppException {
		return storage.getChirps();
	}

	@Override
	public List<Chirp> findChirpsByMessage(String message) throws UserAppException {
		return storage.findChirpsByMessage(message);
	}

	@Override
	public List<Chirp> findChirpsByDate(ZonedDateTime date) throws UserAppException {
		return storage.findChirpsByDate(date.toString());
	}

	@Override
	public List<Chirp> findChirpsByUser(User user) throws UserAppException {
		return storage.findChirpsByUser(user);
	}

	@Override
	public void addChirp(String message, ZonedDateTime date, User user) throws UserAppException {
		Chirp chirp = new Chirp(message, date, user);
		storage.addChirp(chirp);
	}

}
