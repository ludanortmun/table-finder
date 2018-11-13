package com.example.ludanortmun.tablefinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    EditText usernameText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameText = findViewById(R.id.username_edit_text);
        passwordText = findViewById(R.id.password_edit_text);
    }

    public void onRegisterClick(View view) {
        Intent registerActivity = new Intent(view.getContext(), RegisterActivity.class);
        startActivity(registerActivity);
    }

    public void onSigninClick(View view) {
        CognitoHelper cognitoHelper = CognitoHelper.getInstance(this);
        boolean signInResult = cognitoHelper.login(usernameText.getText().toString(), passwordText.getText().toString());
        if(signInResult) {
            Intent welcomeActivity = new Intent(this.getApplicationContext(), LoginSuccessActivity.class);
            finish();
            startActivity(welcomeActivity);
        }
        else {
            Toast.makeText(getApplicationContext(), "Error. Check your credentials or verify your email address", Toast.LENGTH_LONG).show();
        }
    }
}
