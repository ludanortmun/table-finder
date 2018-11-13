package com.example.ludanortmun.tablefinder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginSuccessActivity extends Activity {
    UserProperties properties;
    CognitoHelper cognitoHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        cognitoHelper = CognitoHelper.getInstance(getApplicationContext());
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
}
