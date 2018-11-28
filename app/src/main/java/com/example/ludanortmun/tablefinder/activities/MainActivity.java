package com.example.ludanortmun.tablefinder.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ludanortmun.tablefinder.R;
import com.example.ludanortmun.tablefinder.model.UserProperties;
import com.example.ludanortmun.tablefinder.utils.CognitoHelper;
import com.example.ludanortmun.tablefinder.utils.CognitoUserPoolProvider;
import com.example.ludanortmun.tablefinder.utils.DynamoDBProvider;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    CognitoHelper cognitoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CognitoUserPoolProvider.setContext(getApplicationContext());
        DynamoDBProvider.setContext(getApplicationContext());

        cognitoHelper = CognitoHelper.getInstance();

        if (cognitoHelper.isLoggedIn()) {
            Log.d(TAG, "onCreate: signed in");
            Intent welcomeScreen = new Intent(this, LoginSuccessActivity.class);
            startActivity(welcomeScreen);
        }

        else {
            Log.d(TAG, "onCreate: signed in");
            Intent loginScreen = new Intent(this, LoginActivity.class);
            startActivityForResult(loginScreen, 0);
        }


        UserProperties parameters =
                new UserProperties("ludanortmun", "danort.97@gmail.com",
                        "Testing12#", "Daniel",
                        "1997-04-22", "Male",
                        "Tijuana", "RPG", "Meh");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (cognitoHelper.isLoggedIn()) {
            Log.d(TAG, "onCreate: signed in");
            Intent welcomeScreen = new Intent(this, LoginSuccessActivity.class);
            startActivity(welcomeScreen);
        }

        else {
            Log.d(TAG, "onCreate: signed in");
            Intent loginScreen = new Intent(this, LoginActivity.class);
            startActivity(loginScreen);
        }
    }
}
