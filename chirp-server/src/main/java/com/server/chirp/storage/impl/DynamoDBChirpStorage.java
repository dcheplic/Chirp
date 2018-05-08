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
		if(database.getTable("chirps") == null)
			return null;
		return database.getTable("chirps");
	}

	@Override
	public Chirp[] getChirps() throws StorageException {
		Table table = getTable(getDB());
		ItemCollection<ScanOutcome> collection = table.scan();
		ArrayList<Chirp> result = new ArrayList<Chirp>();
		for(Item item : collection)
			result.add(Chirp.fromItem(item));
		Chirp[] returner = new Chirp[result.size()];
		for(int i = 0; i < returner.length; i++)
			returner[i] = result.get(i);
		return returner;
	}

	@Override
	public Chirp[] findChirpsByMessage(String message) throws StorageException {
		Table table = getTable(getDB());
		ItemCollection<ScanOutcome> collection = table.scan();
		ArrayList<Chirp> result = new ArrayList<Chirp>();
		for(Item item : collection)
			if(Chirp.fromItem(item).getMessage().equals(message))
				result.add(Chirp.fromItem(item));
		Chirp[] returner = new Chirp[result.size()];
		for(int i = 0; i < returner.length; i++)
			returner[i] = result.get(i);
		return returner;
	}

	@Override
	public Chirp[] findChirpsByDate(String date) throws StorageException {
		Table table = getTable(getDB());
		ItemCollection<ScanOutcome> collection = table.scan();
		ArrayList<Chirp> result = new ArrayList<Chirp>();
		for(Item item : collection)
			if(Chirp.fromItem(item).getDate().toString().equals(date))
				result.add(Chirp.fromItem(item));
		Chirp[] returner = new Chirp[result.size()];
		for(int i = 0; i < returner.length; i++)
			returner[i] = result.get(i);
		return returner;
	}

	@Override
	public Chirp[] findChirpsByUser(String id) throws StorageException {
		Table table = getTable(getDB());
		ItemCollection<ScanOutcome> collection = table.scan();
		ArrayList<Chirp> result = new ArrayList<Chirp>();
		for(Item item : collection)
			if(Chirp.fromItem(item).getUserId().equals(id))
				result.add(Chirp.fromItem(item));
		Chirp[] returner = new Chirp[result.size()];
		for(int i = 0; i < returner.length; i++)
			returner[i] = result.get(i);
		return returner;
	}

	@Override
	public void addChirp(Chirp chirp) throws StorageException {
		Table table = getTable(getDB());
		Item item = new Item();
		chirp.fillItem(item);
		table.putItem(item);

	}

	@Override
	public Chirp[] findChirpsContainingUser(String handle) throws StorageException {
		Table table = getTable(getDB());
		ItemCollection<ScanOutcome> collection = table.scan();
		ArrayList<Chirp> result = new ArrayList<Chirp>();
		for(Item item : collection)
			if(Chirp.fromItem(item).getMessage().contains(handle))
				result.add(Chirp.fromItem(item));
		Chirp[] returner = new Chirp[result.size()];
		for(int i = 0; i < returner.length; i++)
			returner[i] = result.get(i);
		return returner;
	}

}
