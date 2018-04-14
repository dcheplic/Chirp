package com.server.chirp.storage.impl;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.server.chirp.model.Chirp;
import com.server.chirp.storage.ChirpStorage;
import com.server.chirp.util.DynamoDBConnectionManager;
import com.server.chirp.util.StorageException;

public class DynamoDBChirpStorage implements ChirpStorage{

	private DynamoDB getDB() {
		return new DynamoDBConnectionManager().get().getDB();
	}

	private Table getTable(DynamoDB database) {
		return database.getTable("chirps");
	}

	@Override
	public List<Chirp> getChirps() throws StorageException {
		Table table = getTable(getDB());
		ItemCollection<ScanOutcome> collection = table.scan();
		ArrayList<Chirp> result = new ArrayList<Chirp>();
		for(Item item : collection)
			result.add(Chirp.fromItem(item));
		return result;
	}

	@Override
	public List<Chirp> findChirpsByMessage(String message) throws StorageException {
		Table table = getTable(getDB());
		ItemCollection<ScanOutcome> collection = table.scan();
		ArrayList<Chirp> result = new ArrayList<Chirp>();
		for(Item item : collection)
			if(Chirp.fromItem(item).getMessage() == message)
				result.add(Chirp.fromItem(item));
		return result;
	}

	@Override
	public List<Chirp> findChirpsByDate(String date) throws StorageException {
		Table table = getTable(getDB());
		ItemCollection<ScanOutcome> collection = table.scan();
		ArrayList<Chirp> result = new ArrayList<Chirp>();
		for(Item item : collection)
			if(Chirp.fromItem(item).getDate().toString() == date)
				result.add(Chirp.fromItem(item));
		return result;
	}

	@Override
	public List<Chirp> findChirpsByUser(String id) throws StorageException {
		Table table = getTable(getDB());
		ItemCollection<ScanOutcome> collection = table.scan();
		ArrayList<Chirp> result = new ArrayList<Chirp>();
		for(Item item : collection)
			if(Chirp.fromItem(item).getUserId() == id)
				result.add(Chirp.fromItem(item));
		return result;
	}

	@Override
	public void addChirp(Chirp chirp) throws StorageException {
		Table table = getTable(getDB());
		Item item = new Item();
		chirp.fillItem(item);
		table.putItem(item);

	}

	@Override
	public List<Chirp> findChirpsContainingUser(String handle) throws StorageException {
		Table table = getTable(getDB());
		ItemCollection<ScanOutcome> collection = table.scan();
		ArrayList<Chirp> result = new ArrayList<Chirp>();
		for(Item item : collection)
			if(Chirp.fromItem(item).getMessage().contains(handle))
				result.add(Chirp.fromItem(item));
		return result;
	}

}
