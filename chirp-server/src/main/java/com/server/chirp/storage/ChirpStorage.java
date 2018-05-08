package com.server.chirp.storage;

import java.util.List;

import com.server.chirp.model.Chirp;
import com.server.chirp.util.StorageException;

public interface ChirpStorage{
	public Chirp[] getChirps() throws StorageException; //returns list of all Chirps
	public Chirp[] findChirpsByMessage(String Message) throws StorageException; //returns list of Chirps matching message
	public Chirp[] findChirpsByDate(String date) throws StorageException; //returns list of Chirps matching date
	public Chirp[] findChirpsByUser(String id) throws StorageException; //returns list of Chirps matching user id
	public void addChirp(Chirp chirp) throws StorageException; //creates new Chirp
	public Chirp[] findChirpsContainingUser(String handle) throws StorageException;
}
