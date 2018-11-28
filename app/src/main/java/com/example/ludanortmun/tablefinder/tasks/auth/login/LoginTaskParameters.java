package com.example.ludanortmun.tablefinder.tasks.auth.login;

import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;

public class LoginTaskParameters {
    private CognitoUser user;
    private String username;
    private String password;
    private boolean result;
    private CognitoCredentialsProvider credentialsProvider;

    public LoginTaskParameters(CognitoUser user, String username, String password, CognitoCredentialsProvider credentialsProvider) {
        this.user = user;
        this.username = username;
        this.password = password;
        result = false;
        this.credentialsProvider = credentialsProvider;
    }

    public CognitoUser getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public CognitoCredentialsProvider getCredentialsProvider() {
        return credentialsProvider;
    }
}
