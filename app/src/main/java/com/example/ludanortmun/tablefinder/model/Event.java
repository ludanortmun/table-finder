package com.example.ludanortmun.tablefinder.model;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@DynamoDBTable(tableName="tablefinder-events")
public class Event implements Serializable {
    private String eventId;
    private String hostName;
    private String hostId;
    private String address;
    private String date;
    private String time;
    private String description;
    private String games;

    public Event(String eventId, String hostName, String hostId, String address, String time, String date, String description, String games) {

        this.eventId = eventId;
        this.hostName = hostName;
        this.hostId = hostId;
        this.address = address;
        this.time = time;
        this.description = description;
        this.games = games;
        this.date = date;
    }

    public Event() { }

    @DynamoDBHashKey(attributeName = "eventId")
    @DynamoDBAttribute(attributeName = "eventId")
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @DynamoDBAttribute(attributeName = "hostName")
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @DynamoDBAttribute(attributeName = "hostId")
    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    @DynamoDBAttribute(attributeName = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @DynamoDBAttribute(attributeName = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "games")
    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }

    @DynamoDBAttribute(attributeName = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
