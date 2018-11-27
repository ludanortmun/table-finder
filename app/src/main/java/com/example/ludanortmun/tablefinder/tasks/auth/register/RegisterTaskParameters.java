package com.example.ludanortmun.tablefinder.tasks.auth.register;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.example.ludanortmun.tablefinder.model.UserProperties;

public class RegisterTaskParameters {

    private CognitoUserPool userPool;
    private UserProperties properties;
    private boolean result;

    public RegisterTaskParameters(CognitoUserPool userPool, UserProperties properties) {
        this.userPool = userPool;
        this.properties = properties;
    }

    public CognitoUserPool getUserPool() {

        return userPool;
    }

    public UserProperties getProperties() {
        return properties;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
