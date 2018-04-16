package com.server.chirp.service.impl;

import java.util.Date;
import java.util.List;

import com.server.chirp.model.Chirp;
import com.server.chirp.service.ChirpService;
import com.server.chirp.storage.impl.DynamoDBChirpStorage;
import com.server.chirp.util.UserAppException;

public class DynamoDBChirpService implements ChirpService{

	DynamoDBChirpStorage storage;
	
	public DynamoDBChirpService(DynamoDBChirpStorage storage) {
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
	public List<Chirp> findChirpsByDate(Date date) throws UserAppException {
		return storage.findChirpsByDate(date.toString());
	}

	@Override
	public List<Chirp> findChirpsByUser(String id) throws UserAppException {
		return storage.findChirpsByUser(id);
	}
	

	@Override
	public List<Chirp> findChirpsContainingUser(String handle) throws UserAppException {
		return storage.findChirpsContainingUser(handle);
	}

	@Override
	public void addChirp(String message, Date date, String userId) throws UserAppException {
		Chirp chirp = new Chirp(message, date, userId);
		storage.addChirp(chirp);
	}
}
