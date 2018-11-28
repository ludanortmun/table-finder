package com.example.ludanortmun.tablefinder.utils;

import android.content.Context;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.example.ludanortmun.tablefinder.model.UserProperties;
import com.example.ludanortmun.tablefinder.tasks.auth.login.LoginTask;
import com.example.ludanortmun.tablefinder.tasks.auth.login.LoginTaskParameters;
import com.example.ludanortmun.tablefinder.tasks.auth.logout.LogoutTask;
import com.example.ludanortmun.tablefinder.tasks.auth.logout.LogoutTaskParameters;
import com.example.ludanortmun.tablefinder.tasks.auth.misc.CheckSignedInParameters;
import com.example.ludanortmun.tablefinder.tasks.auth.misc.CheckSignedInTask;
import com.example.ludanortmun.tablefinder.tasks.auth.misc.GetUserDetailsTask;
import com.example.ludanortmun.tablefinder.tasks.auth.misc.GetUserDetailsTaskParameters;
import com.example.ludanortmun.tablefinder.tasks.auth.register.RegisterTask;
import com.example.ludanortmun.tablefinder.tasks.auth.register.RegisterTaskParameters;

import java.util.concurrent.ExecutionException;

public class CognitoHelper {
    private static CognitoHelper instance;

    private CognitoUserPool userPool;

    private CognitoHelper() {
        this.userPool = CognitoUserPoolProvider.provideCognitoUserPool();
    }

    public static CognitoHelper getInstance() {
        if (instance == null) {
            instance = new CognitoHelper();
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

    public boolean login(final String username, final String password) {
        boolean result = false;

        LoginTaskParameters parameters = new LoginTaskParameters(userPool.getUser(username), username, password, DynamoDBProvider.getCognitoCachingCredentialsProvider());
        try {
            result = new LoginTask().execute(parameters).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean isLoggedIn() {
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
        result.setUsername(userPool.getCurrentUser().getUserId());
        return result;
    }
}