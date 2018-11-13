package com.example.ludanortmun.tablefinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginSuccessActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        CognitoHelper cognitoHelper = CognitoHelper.getInstance(getApplicationContext());
        ((TextView)findViewById(R.id.greeting_text)).setText("Welcome, " + cognitoHelper.getUserInfo().name);
    }

    public void onLogoutClick(View view) {
        CognitoHelper.getInstance(getApplicationContext()).logout();
        finish();
    }
}
