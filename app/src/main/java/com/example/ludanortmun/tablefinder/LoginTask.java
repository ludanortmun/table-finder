package com.example.ludanortmun.tablefinder;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;

public class LoginTask extends AsyncTask<LoginTaskParameters, Void, Boolean> {
    @Override
    protected Boolean doInBackground(LoginTaskParameters... loginTaskParameters) {
        final LoginTaskParameters parameters = loginTaskParameters[0];

        AuthenticationHandler handler = new AuthenticationHandler() {
            @Override
            public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
                parameters.setResult(true);
            }

            @Override
            public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {
                AuthenticationDetails authDetails = new AuthenticationDetails(parameters.getUsername(), parameters.getPassword(), null);
                authenticationContinuation.setAuthenticationDetails(authDetails);
                authenticationContinuation.continueTask();
            }

            @Override
            public void getMFACode(MultiFactorAuthenticationContinuation continuation) {

            }

            @Override
            public void authenticationChallenge(ChallengeContinuation continuation) {

            }

            @Override
            public void onFailure(Exception exception) {
                parameters.setResult(false);
            }
        };

        parameters.getUser().getSession(handler);

        return parameters.getResult();
    }
}


class LoginTaskParameters {
    private CognitoUser user;
    private String username;
    private String password;
    private boolean result;

    public LoginTaskParameters(CognitoUser user, String username, String password) {
        this.user = user;
        this.username = username;
        this.password = password;
        result = false;
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
}