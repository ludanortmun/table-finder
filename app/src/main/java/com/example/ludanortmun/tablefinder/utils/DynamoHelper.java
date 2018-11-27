package com.example.ludanortmun.tablefinder.utils;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.example.ludanortmun.tablefinder.model.Event;

import java.util.ArrayList;
import java.util.List;

public class DynamoHelper {

    public static List<Event> getAllEvents() {
        List<Event> result = new ArrayList<>();
        DynamoDBMapper mapper = DynamoDBProvider.getDynamoDBMapper();
        PaginatedList<Event> scanResult = mapper.scan(Event.class, new DynamoDBScanExpression());

        for (Event e : scanResult) {
            result.add(e);
        }

        return result;
    }

    public static Event getEventById(String eventId) {
        DynamoDBMapper mapper = DynamoDBProvider.getDynamoDBMapper();
        return mapper.load(Event.class, eventId);
    }

    public static void saveEvent(Event event) {
        DynamoDBMapper mapper = DynamoDBProvider.getDynamoDBMapper();
        mapper.save(event);
    }
}
