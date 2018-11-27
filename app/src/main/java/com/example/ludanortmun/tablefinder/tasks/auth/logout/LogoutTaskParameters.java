package com.example.ludanortmun.tablefinder.tasks.auth.logout;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;

public class LogoutTaskParameters {

    private CognitoUser user;

    public LogoutTaskParameters(CognitoUser user) {
        this.user = user;
    }

    public CognitoUser getUser() {


        return user;
    }
}
