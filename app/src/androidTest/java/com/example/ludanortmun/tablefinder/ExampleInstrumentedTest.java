package com.example.ludanortmun.tablefinder;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.example.ludanortmun.tablefinder.model.Event;
import com.example.ludanortmun.tablefinder.utils.CognitoHelper;
import com.example.ludanortmun.tablefinder.utils.CognitoUserPoolProvider;
import com.example.ludanortmun.tablefinder.utils.DynamoDBProvider;
import com.example.ludanortmun.tablefinder.utils.DynamoHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.ludanortmun.tablefinder", appContext.getPackageName());
    }
}
