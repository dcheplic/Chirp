package com.server.chirp.storage.impl;

import java.util.ArrayList;
import java.util.List;

import com.server.chirp.model.Chirp;
import com.server.chirp.storage.ChirpStorage;
import com.server.chirp.util.StorageException;

public class InMemoryChirpStorage implements ChirpStorage{
	
	private ArrayList<Chirp> chirps;
	
	public InMemoryChirpStorage() {
		chirps = new ArrayList<>();
	}

	@Override
	public List<Chirp> getChirps() throws StorageException {
		return chirps;
	}

	@Override
	public List<Chirp> findChirpsByMessage(String message) throws StorageException {
		List<Chirp> result = new ArrayList<Chirp>();
		for(Chirp chirp : chirps)
			if(chirp.getMessage().equals(message))
				result.add(chirp);
		return result;
	}

	@Override
	public List<Chirp> findChirpsByDate(String date) throws StorageException {
		List<Chirp> result = new ArrayList<Chirp>();
		for(Chirp chirp : chirps)
			if(chirp.getDate().toString().equals(date))
				result.add(chirp);
		return result;
	}

	@Override
	public List<Chirp> findChirpsByUser(String id) throws StorageException {
		List<Chirp> result = new ArrayList<Chirp>();
		for(Chirp chirp : chirps)
			if(chirp.getUserId().toString().equals(id))
				result.add(chirp);
		return result;
	}
	
	@Override
	public List<Chirp> findChirpsContainingUser(String handle) throws StorageException {
		List<Chirp> result = new ArrayList<Chirp>();
		for(Chirp chirp : chirps)
			if(chirp.getMessage().contains("&" + handle))
				result.add(chirp);
		return result;
	}

	@Override
	public void addChirp(Chirp chirp) throws StorageException {
		chirps.add(chirp);
	}

}
