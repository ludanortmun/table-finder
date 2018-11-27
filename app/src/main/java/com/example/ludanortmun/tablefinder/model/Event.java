package com.example.ludanortmun.tablefinder.model;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.Date;
import java.util.List;

@DynamoDBTable(tableName="tablefinder-events")
public class Event {
    private String eventId;
    private String hostName;
    private String hostId;
    private String address;
    private Date time;
    private String description;
    private List<String> games;

    public Event(String eventId, String hostName, String hostId, String address, Date time, String description, List<String> games) {

        this.eventId = eventId;
        this.hostName = hostName;
        this.hostId = hostId;
        this.address = address;
        this.time = time;
        this.description = description;
        this.games = games;
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
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
    public List<String> getGames() {
        return games;
    }

    public void setGames(List<String> games) {
        this.games = games;
    }
}
