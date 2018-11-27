package com.example.ludanortmun.tablefinder.tasks.auth.misc;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.example.ludanortmun.tablefinder.model.UserProperties;

public class GetUserDetailsTaskParameters {
    private CognitoUser user;

    public UserProperties getResult() {
        return result;
    }

    public void setResult(UserProperties result) {
        this.result = result;
    }

    public CognitoUser getUser() {

        return user;
    }

    public GetUserDetailsTaskParameters(CognitoUser user) {

        this.user = user;
    }

    private UserProperties result;
}
