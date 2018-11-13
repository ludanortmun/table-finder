package com.example.ludanortmun.tablefinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    CognitoHelper cognitoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cognitoHelper = CognitoHelper.getInstance(getApplicationContext());

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
