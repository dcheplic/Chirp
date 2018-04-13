package com.server.chirp.storage;

import java.util.List;

import com.server.chirp.model.Chirp;
import com.server.chirp.model.User;
import com.server.chirp.util.StorageException;

public interface ChirpStorage {
	public List<Chirp> getChirps() throws StorageException; //returns list of all Chirps
	public List<Chirp> findChirpsByMessage(String Message) throws StorageException; //returns list of Chirps matching message
	public List<Chirp> findChirpsByDate(String date) throws StorageException; //returns list of Chirps matching date
	public List<Chirp> findChirpsByUser(String id) throws StorageException; //returns list of Chirps matching user id
	public void addChirp(Chirp chirp) throws StorageException; //creates new Chirp
	List<Chirp> findChirpsContainingUser(String handle) throws StorageException;
}
