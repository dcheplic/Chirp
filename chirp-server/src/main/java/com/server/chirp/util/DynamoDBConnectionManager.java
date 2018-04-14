package com.server.chirp.util;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

public class DynamoDBConnectionManager {
	
	private static DynamoDBConnectionManager soleInstance;
	
	public static DynamoDBConnectionManager get() {
		if (soleInstance == null)
			soleInstance = new DynamoDBConnectionManager();
		return soleInstance;
	}
	
	private AmazonDynamoDB client;
	
	public DynamoDBConnectionManager() {
		client = AmazonDynamoDBClientBuilder.standard()
				.withRegion(Regions.US_EAST_1)
				.build();
	}
	
	public DynamoDB getDB() {
		return new DynamoDB(client);
	}
}
