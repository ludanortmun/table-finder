package com.example.ludanortmun.tablefinder.utils;

import android.content.Context;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class DynamoDBProvider {
    private static final String IDENTITY_POOL_ID = "";
    private static AmazonDynamoDB instance;
    private static Context context;
    private static CognitoCachingCredentialsProvider credentialsProvider;
    private static DynamoDBMapper mapper;

    public static AmazonDynamoDB getAmazonDynamoDBClientInstance() {
        if (instance == null) {
            instance = Region.getRegion(Regions.US_WEST_2).createClient(AmazonDynamoDBClient.class,
                    getCognitoCachingCredentialsProvider(),
                    new ClientConfiguration()
            );
        }

        return instance;
    }

    public static CognitoCachingCredentialsProvider getCognitoCachingCredentialsProvider() {
        if (credentialsProvider == null) {
            credentialsProvider = new CognitoCachingCredentialsProvider(context, IDENTITY_POOL_ID, Regions.US_WEST_2);
        }
        return credentialsProvider;
    }

    public static DynamoDBMapper getDynamoDBMapper() {
        if (mapper == null) {
            mapper = DynamoDBMapper.builder()
                    .dynamoDBClient(instance == null ? getAmazonDynamoDBClientInstance() : instance)
                    .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                    .build();
        }

        return mapper;
    }

    public static void setContext(Context c) {
        context = c;
    }
}
