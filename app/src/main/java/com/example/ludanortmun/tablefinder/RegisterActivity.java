package com.example.ludanortmun.tablefinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    CognitoHelper cognitoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        cognitoHelper = CognitoHelper.getInstance(getApplicationContext());
    }

    protected void onRegisterClick(View view) {

        if (!passwordConfirmationMatches()) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        String username = getTextFromEditTextView(R.id.username_edit_text);
        String password = getTextFromEditTextView(R.id.password_edit_text);
        String name = getTextFromEditTextView(R.id.name_edit_text);
        String email = getTextFromEditTextView(R.id.email_edit_text);
        String city = getTextFromEditTextView(R.id.location);
        String gender = getTextFromEditTextView(R.id.gender_option);
        String birthdate = getTextFromEditTextView(R.id.birthdate_edit_text);
        String favoriteGenre = getTextFromEditTextView(R.id.favorite_genre);
        String about = getTextFromEditTextView(R.id.about);

        UserProperties properties = new UserProperties(username, email, password, name, birthdate,
                gender, city, favoriteGenre, about);

        boolean success = cognitoHelper.register(properties);

        if (success) {
            Toast.makeText(this.getApplicationContext(), "Registration successful. Please verify your email in order to login", Toast.LENGTH_LONG).show();
            finish();
        }

        else {
            Toast.makeText(this.getApplicationContext(), "Registration failed. Try again with another username", Toast.LENGTH_LONG).show();
        }

    }

    protected boolean passwordConfirmationMatches() {
        String password = getTextFromEditTextView(R.id.password_edit_text);
        String passwordConfirmation = getTextFromEditTextView(R.id.passwordConfirm_edit_text);

        return password.equals(passwordConfirmation);
    }

    protected String getTextFromEditTextView(int viewId) {
        EditText view = findViewById(viewId);
        return view.getText().toString();
    }
}
