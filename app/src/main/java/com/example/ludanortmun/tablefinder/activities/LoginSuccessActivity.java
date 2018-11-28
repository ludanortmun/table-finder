package com.example.ludanortmun.tablefinder.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ludanortmun.tablefinder.R;
import com.example.ludanortmun.tablefinder.model.UserProperties;
import com.example.ludanortmun.tablefinder.utils.CognitoHelper;

public class LoginSuccessActivity extends Activity {
    UserProperties properties;
    CognitoHelper cognitoHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        cognitoHelper = CognitoHelper.getInstance();
        properties = cognitoHelper.getUserInfo();
        ((TextView)findViewById(R.id.greeting_text)).setText("Welcome, " +
                (properties == null? "PLACEHOLDER": properties.getName())
        );
    }

    public void onLogoutClick(View view) {
        cognitoHelper.logout();
        Intent loginActivity = new Intent(this.getApplicationContext(), LoginActivity.class);
        finish();
        startActivity(loginActivity);
    }
    public void onViewEventsClick(View view) {
        Intent viewEventsActivity = new Intent(this.getApplicationContext(), ViewAllEventsActivity.class);
        startActivity(viewEventsActivity);
    }
    public void onCreateEventClick(View view) {
        Intent createEventActivity = new Intent(this.getApplicationContext(), CreateEventActivity.class);
        startActivity(createEventActivity);
    }
}
