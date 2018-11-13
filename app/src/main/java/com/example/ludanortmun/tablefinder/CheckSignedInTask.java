package com.example.ludanortmun.tablefinder;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;

public class CheckSignedInTask extends AsyncTask<CheckSignedInParameters, Void, Boolean> {
    @Override
    protected Boolean doInBackground(CheckSignedInParameters... checkSignedInParameters) {
        final CheckSignedInParameters parameters = checkSignedInParameters[0];

        AuthenticationHandler handler = new AuthenticationHandler() {
            @Override
            public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
                parameters.setResult(userSession.isValid());
            }

            @Override
            public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {
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

class CheckSignedInParameters {
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
