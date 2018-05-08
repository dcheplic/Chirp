package com.server.chirp.service.impl;

import java.util.Date;
import java.util.List;

import com.server.chirp.model.Chirp;
import com.server.chirp.service.ChirpService;
import com.server.chirp.storage.ChirpStorage;
import com.server.chirp.util.UserAppException;

public class ChirpServiceImpl implements ChirpService{
	
	private ChirpStorage storage;
	
	public ChirpServiceImpl(ChirpStorage storage) {
		this.storage = storage;
	}

	@Override
	public Chirp[] getChirps() throws UserAppException {
		return storage.getChirps();
	}

	@Override
	public Chirp[] findChirpsByMessage(String message) throws UserAppException {
		return storage.findChirpsByMessage(message);
	}

	@Override
	public Chirp[] findChirpsByDate(String date) throws UserAppException {
		return storage.findChirpsByDate(date.toString());
	}

	@Override
	public Chirp[] findChirpsByUser(String id) throws UserAppException {
		return storage.findChirpsByUser(id);
	}
	

	@Override
	public Chirp[] findChirpsContainingUser(String handle) throws UserAppException {
		return storage.findChirpsContainingUser(handle);
	}

	@Override
	public void addChirp(String message, String date, String userId) throws UserAppException {
		Chirp chirp = new Chirp(message, date, userId);
		storage.addChirp(chirp);
	}
}
