package com.example.ludanortmun.tablefinder;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;

import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class CognitoHelper {
    private static CognitoHelper instance;

    private CognitoUserPool userPool;

    private CognitoHelper(Context context) {
        this.userPool = CognitoUserPoolProvider.provideCognitoUserPool(context);
    }

    public static CognitoHelper getInstance(Context context) {
        if (instance == null) {
            instance = new CognitoHelper(context);
        }

        return instance;
    }

    public boolean register(UserProperties properties) {
        boolean result = false;
        RegisterTaskParameters parameters = new RegisterTaskParameters(userPool, properties);
        try {
            result = new RegisterTask().execute(parameters).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    boolean login(final String username, final String password) {
        boolean result = false;

        LoginTaskParameters parameters = new LoginTaskParameters(userPool.getUser(username), username, password);
        try {
            result = new LoginTask().execute(parameters).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    boolean isLoggedIn() {
        boolean result = false;
        CheckSignedInParameters parameters = new CheckSignedInParameters(userPool.getCurrentUser());
        try {
            result = new CheckSignedInTask().execute(parameters).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void logout() {
        LogoutTaskParameters parameters = new LogoutTaskParameters(userPool.getCurrentUser());
        try {
            new LogoutTask().execute(parameters).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public UserProperties getUserInfo() {
        if (!isLoggedIn()) {
            return null;
        }

        GetUserDetailsTaskParameters parameters = new GetUserDetailsTaskParameters(userPool.getCurrentUser());

        UserProperties result = null;

        try {
            result = new GetUserDetailsTask().execute(parameters).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}