package com.server.chirp.storage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.server.chirp.model.Chirp;
import com.server.chirp.model.User;
import com.server.chirp.storage.ChirpStorage;
import com.server.chirp.util.StorageException;

public class InMemoryChirpStorage implements ChirpStorage{
	
	Map<User, Chirp> chirps;
	
	public InMemoryChirpStorage() {
		chirps = new HashMap<>();
	}

	@Override
	public List<Chirp> getChirps() throws StorageException {
		return new ArrayList<Chirp>(chirps.values());
	}

	@Override
	public List<Chirp> findChirpsByMessage(String message) throws StorageException {
		List<Chirp> result = new ArrayList<Chirp>();
		for(Chirp chirp : chirps.values())
			if(chirp.getMessage().equals(message))
				result.add(chirp);
		return result;
	}

	@Override
	public List<Chirp> findChirpsByDate(String date) throws StorageException {
		List<Chirp> result = new ArrayList<Chirp>();
		for(Chirp chirp : chirps.values())
			if(chirp.getDate().toString().equals(date))
				result.add(chirp);
		return result;
	}

	@Override
	public List<Chirp> findChirpsByUser(String id) throws StorageException {
		List<Chirp> result = new ArrayList<Chirp>();
		for(Chirp chirp : chirps.values())
			if(chirp.getUser().getId().toString().equals(id))
				result.add(chirp);
		return result;
	}

	@Override
	public void addChirp(Chirp chirp) throws StorageException {
		chirps.put(chirp.getUser(), chirp);
	}

}
