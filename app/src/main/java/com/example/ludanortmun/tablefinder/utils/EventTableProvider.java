package com.example.ludanortmun.tablefinder.utils;

import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

public class EventTableProvider {
    private static final String TABLE_NAME = "tablefinder-events";

    private static Table tableInstance;
    private static AmazonDynamoDB client;

    public static Table getTable() {
        if (tableInstance == null) {
            tableInstance = Table.loadTable(client, TABLE_NAME);
        }

        return tableInstance;
    }

    public static void setAmazonDBClient(AmazonDynamoDB amazonDBClient) {
        client = amazonDBClient;
    }
}
