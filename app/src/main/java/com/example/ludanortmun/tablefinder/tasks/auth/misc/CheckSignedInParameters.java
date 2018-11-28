package com.example.ludanortmun.tablefinder.tasks.auth.misc;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;

public class CheckSignedInParameters {
    private CognitoUser user;
    private boolean result;

    public CheckSignedInParameters(CognitoUser user) {

        this.user = user;
        result = false;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public CognitoUser getUser() {

        return user;
    }
}
